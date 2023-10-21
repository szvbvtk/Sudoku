import sac.graph.BreadthFirstSearch;
import sac.graph.GraphSearchConfigurator;
import sac.graph.GraphState;

public class Main {
    public static void main(String[] args) {
        String str = "...1.3..9.......5..7..543..49.3.56......7..91.....9..81...36....5.941.........8..";

//        Sudoku s_input = new Sudoku(str, 3);
//        String s_output = s_input.toString();
//
//        System.out.println(s_output);
//
//        for(GraphState g: s_input.generateChildren()){
//            System.out.println(g);
//        }
//
//        GraphSearchConfigurator gsc = new GraphSearchConfigurator();
//        gsc.setWantedNumberOfSolutions(Integer.MAX_VALUE);
//
//        BreadthFirstSearch bfs = new BreadthFirstSearch(s_input, gsc);
//        bfs.execute();
//
//        System.out.println(bfs.getSolutions().size());
//        System.out.println(bfs.getSolutions());

        Sudoku2 s_input = new Sudoku2(str, 3);
        GraphSearchConfigurator gsc = new GraphSearchConfigurator();
        gsc.setWantedNumberOfSolutions(Integer.MAX_VALUE);
        BreadthFirstSearch bfs = new BreadthFirstSearch(s_input, gsc);
        bfs.execute();
        System.out.println(bfs.getSolutions().size());
        System.out.println(bfs.getSolutions());
    }
}