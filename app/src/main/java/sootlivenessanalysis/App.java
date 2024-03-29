/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package sootlivenessanalysis;

import java.io.File;

import soot.G;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.jimple.JimpleBody;
import soot.options.Options;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

public class App {
    public static String clsName = "FizzBuzz";
    public static String methodName = "printFizzBuzz";
    public static String programDirectory = System.getProperty("user.dir") + File.separator 
            + "src" + File.separator 
            + "main" + File.separator 
            + "java" + File.separator 
            + "sootlivenessanalysis" + File.separator 
            + "programs";

    public static void setupSoot() {
        G.reset();
        Options.v().set_prepend_classpath(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_soot_classpath(programDirectory);
        SootClass sc = Scene.v().loadClassAndSupport(clsName);
        sc.setApplicationClass();
        Scene.v().loadNecessaryClasses();
    }

    public static void main(String[] args) {
        System.out.println(programDirectory.toString());

        setupSoot();

        SootClass mainClass = Scene.v().getSootClass(clsName);
        SootMethod sm = mainClass.getMethodByName(methodName);
        JimpleBody body = (JimpleBody) sm.retrieveActiveBody();

        // System.out.println(body);

        UnitGraph g = new BriefUnitGraph(body);

        System.out.println();
        System.out.println("Program Unit Graph:");
        System.out.println();

        System.out.println(g);

        // Instantiate analysis and collect results
        // LiveAnalysis liveVariables = new LiveAnalysis(g);

        LiveAnalysisWrapper la = new LiveAnalysisWrapper(g);

        // System.out.println(liveVariables);

        // return SparseArraySets of live variables:
        // liveVariables.getFlowBefore(s);
        // liveVariables.getFlowAfter(s);
    }
}
