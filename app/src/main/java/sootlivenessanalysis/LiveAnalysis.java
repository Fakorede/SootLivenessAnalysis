package sootlivenessanalysis;

import soot.*;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.*;

@SuppressWarnings("rawtypes")
public class LiveAnalysis extends BackwardFlowAnalysis {
    /**
     * Calls constructor of the super class, and passes it the graph. Then performs the analysis.
     * @param g
     */
    public LiveAnalysis(UnitGraph g) {
        super(g);
        doAnalysis();
    }

    /**
     * Initialize IN, OUT sets prior to analysis for maintaining sets of live variables at various points.
     */
    protected Object newInitialFlow() {
        return new ArraySparseSet();
    }
    
    /**
     * Represents the initial set of the entry node
     * @return
     */
    protected Object entryInitialFlow() {
        return new ArraySparseSet();
    }

    /**
     * merge joins the results of the may-use analysis of the successor nodes
     * i.e. combine two FlowSets to produce an out-FlowSet
     * @param src1
     * @param src2
     * @param dest
     */
    protected void merge(Object src1, Object src2, Object dest) {
        FlowSet src1Set = (FlowSet) src1,
        src2Set = (FlowSet) src2,
        destSet = (FlowSet) dest;

        src1Set.union(src2Set, destSet); // dest = src1Set U src2Set
    }

    /**
     * put the source into the destination
     * @param src
     * @param dest
     */
    protected void copy(Object src, Object dest) {
        FlowSet sourceSet = (FlowSet)src,
        destSet = (FlowSet) dest;

        sourceSet.copy(destSet); // destSet = sourceSet
    }

    /**
     * Removes all writes (kill) and adds all reads(gen) from each node.
     * given inSet (srcValue) and node, put the correct OUT value into the outSet (destValue)
     * @param srcValue
     * @param u
     * @param destValue
     */
    protected void flowThrough (Object srcValue, Object node, Object destValue) {
        FlowSet src = (FlowSet) srcValue,
        dest = (FlowSet) destValue;
        Unit ut = (Unit) node;

        src.copy(dest); // dest = src

        /** 
         * perform kill
         * take out kill set: for each local v def’d in this unit, remove v from dest
         */
        for (ValueBox box : ut.getDefBoxes()) {
            Value value = box.getValue();
            if (value instanceof Local)
                dest.remove(value);
        }

        /**
         * perform gen
         * for each local v used in this unit, add v to dest
         */
        for (ValueBox box : ut.getUseBoxes()) {
            Value value = box.getValue();
            if (value instanceof Local)
                dest.add(value);
        }
    }

}
