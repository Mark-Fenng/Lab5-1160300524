package helper;

import Exception.*;
import Exception.Command.CommandException;
import Exception.Command.UnsupportedException;
import Exception.Edge.*;
import Exception.Graph.GraphNullException;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import LoggerFactory.MyLogger;
import graph.Graph;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

abstract class Command {
    protected Graph graph;

    Command(Graph g) {
        graph = g;
    }

    /**
     * 向图中添加输入的新对象
     *
     * @param args 用户输入的新对象的属性
     */
    abstract void add(List<String> args) throws EdgeNullVertexException, VertexAttributeException, VertexTypeException, EdgeTypeException, FormatException, IOException, HyperEdgeException, EdgeWeightException, VertexLabelException, TypeException, DirectedEdgeException, CommandException, GraphNullException;

    /**
     * 从图中删除用户指定的对象
     *
     * @param args 用户输入的指定对象的属性
     */
    abstract void delete(List<String> args) throws EdgeWeightException, CommandException, GraphNullException;

    /**
     * 更新图中指定对象的属性
     *
     * @param args 用户输入的指定对象的属性
     */
    abstract void update(List<String> args) throws VertexAttributeException, EdgeWeightException, CommandException, GraphNullException;

    /**
     * 展示图中指定对象的属性
     *
     * @param args 用户输入的指定对象属性
     */
    abstract void show(List<String> args) throws UnsupportedException, CommandException, GraphNullException;

    /**
     * 在删除指定对象时，向用户确认是否删除
     *
     * @return true: 用户确认删除 false:用户撤销了删除的操作
     */
    static boolean confirm() {
        System.out.println("Are you sure to delete them? (Yes/no)");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }
        boolean choice = input.equals("y") || input.equals("Y") || input.equals("yes") || input.equals("Yes");
        if (!choice)
            MyLogger.info("User choice not to delete them");
        return choice;
    }
}
