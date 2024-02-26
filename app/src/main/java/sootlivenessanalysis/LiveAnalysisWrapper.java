package sootlivenessanalysis;

import java.util.List;
import java.util.Map;

import soot.Unit;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.LiveLocals;

@SuppressWarnings("rawtypes")
public class LiveAnalysisWrapper implements LiveLocals {
    Map unitToLocalsAfter;
    Map unitToLocalsBefore;

    public LiveAnalysisWrapper(UnitGraph graph) {
        System.out.println("Constructor call");

        LiveAnalysis analysis = new LiveAnalysis(graph);

        // TODO: Build unitToLocals map

        // TODO: figure this out!
        //analysis.getFlowAfter();
        //analysis.getFlowAfter();
    }

    public List getLiveLocalsAfter(Unit s) {
        return (List) unitToLocalsAfter.get(s);
    }

    public List getLiveLocalsBefore(Unit s) {
        return (List) unitToLocalsBefore.get(s);
    }

}
