package factory.graph;

import Exception.Edge.EdgeVertexException;
import Exception.FormatException;
import Exception.TypeException;
import Exception.Vertex.VertexAttributeException;
import graph.Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

abstract public class GraphFactory {
    public static Graph createGraph(String filePath) throws IOException, TypeException, FormatException, EdgeVertexException, VertexAttributeException {
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        String content;
        int countLine = 1; // 统计文件的行数，便于报错
        while ((content = fileReader.readLine()).replace(" ", "").equals(""))
            countLine++;
        fileReader.close();
        Pattern typePattern = Pattern.compile("GraphType\\s*=\\s*\"(.*)\"");
        Matcher typeMatcher = typePattern.matcher(content);
        if (typeMatcher.find()) {
            String type = typeMatcher.group(1);
            switch (type) {
                case "GraphPoet":
                    return GraphPoetFactory.createGraph(filePath);
                case "SocialNetwork":
                    return GraphSocialFactory.createGraph(filePath);
                case "NetworkTopology":
                    return GraphTopologyFactory.createGraph(filePath);
                case "MovieGraph":
                    return GraphMovieFactory.createGraph(filePath);
                default:
                    throw new TypeException("Define Graph type format : GraphType=\"GraphPoet\"|\"SocialNetwork\"|\"NetworkTopology\"|\"MovieGraph\"");
            }
        } else {
            throw new FormatException("Miss the Graph Type", countLine);
        }
    }

    /**
     * 从包含图信息的固定格式文件中单独获取图的label值
     *
     * @param filePath 包含图信息的固定格式文件的路径
     * @return 图的Label值
     * @throws IOException     包含图信息的固定格式文件读取的异常
     * @throws FormatException 传入的文件的格式不符合要求异常，处理异常时要求给出异常的提示信息，并允许用户重新读入新的的文件，
     */
    static String GraphLabel(String filePath) throws IOException, FormatException {
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        Pattern regex;
        Matcher matcher;
        String content;
        int countLine = 0; // 统计文件的行数，便于报错
        fileReader.readLine();  // graph type
        countLine++;
        // graph name
        if ((content = fileReader.readLine()) != null) {
            countLine++;
            fileReader.close();
            regex = Pattern.compile("GraphName\\s*=\\s*\"(.*)\"");
            matcher = regex.matcher(content);
            if (matcher.find()) {
                return matcher.group(1);
            } else {
                throw new FormatException("Define Graph label format : GraphName=\"name\"", countLine);
            }
        } else {
            throw new FormatException("Miss the Graph Label", countLine);
        }
    }

    /**
     * 从包含图信息的固定格式文件中单独获取图中点的信息
     *
     * @param filePath 包含图信息的固定格式文件的路径
     * @return 包含图信息的固定格式文件的路径
     * @throws IOException     包含图信息的固定格式文件读取的异常
     * @throws FormatException 传入的文件的格式不符合要求异常，处理异常时要求给出异常的提示信息，并允许用户重新读入新的的文件，
     */
    public static List<List<String>> getVertices(String filePath) throws IOException, FormatException {
        List<List<String>> vertices = new ArrayList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        Pattern regex;
        Matcher matcher;
        String content;
        int countLine = 0; // 统计文件的行数，便于报错
        // 跳过图的类型，图的label的内容
        do {
            countLine++;
        } while (!fileReader.readLine().equals(""));
        content = fileReader.readLine();
        countLine++;
        regex = Pattern.compile("^VertexType\\s*=\\s*\"(\\w+)\"$");
        matcher = regex.matcher(content);
        if (!matcher.find()) {
            throw new FormatException("Miss the Vertex Type", countLine);
        }
        // 找到文件中关于点的描述
        while (!(content = fileReader.readLine()).equals("")) {
            countLine++;
            regex = Pattern.compile("^Vertex\\s*=\\s*<\"(.*)\",\\s*\"(.*)\"(?:,\\s*<(.*)>)?>$");
            matcher = regex.matcher(content);
            if (matcher.find()) {
                String label = matcher.group(1);
                String type = matcher.group(2);
                String attr = matcher.group(3);
                vertices.add(new ArrayList<>(Arrays.asList(label, type, attr)));
            } else {
                throw new FormatException("\"" + content + "\" format error!\nDefine Vertex format : Vertex = <\"Label\",\"type\",<\"attr1\",...,\"attrk\">>", countLine);
            }
        }
        fileReader.close();
        return vertices;
    }

    /**
     * 从包含图信息的固定格式文件中单独获取图中点的信息
     *
     * @param filePath 包含图信息的固定格式文件的路径
     * @return 包含图信息的固定格式文件读取的异常
     * @throws IOException     包含图信息的固定格式文件读取的异常
     * @throws FormatException 传入的文件的格式不符合要求异常，处理异常时要求给出异常的提示信息，并允许用户重新读入新的的文件，
     */
    public static List<List<String>> getEdges(String filePath) throws IOException, FormatException {
        List<List<String>> vertices = new ArrayList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        Pattern regex;
        Matcher matcher;
        String content;
        int countLine = 0; // 统计文件的行数，便于报错
        // 跳过图的类型，图的label的内容
        do {
            countLine++;
        } while (!fileReader.readLine().equals(""));
        // 跳过点的描述信息
        do {
            countLine++;
        } while (!fileReader.readLine().equals(""));
        content = fileReader.readLine();
        countLine++;
        regex = Pattern.compile("^EdgeType\\s*=\\s*\"(\\w+)\"$");
        matcher = regex.matcher(content);
        if (!matcher.find()) {
            throw new FormatException("Miss the Edge Type", countLine);
        }
        // 找到文件中关于点的描述
        while ((content = fileReader.readLine()) != null && !content.equals("")) {
            countLine++;
            regex = Pattern.compile("^Edge\\s*=\\s*<\"(.*)\",\\s*\"(.*)\",\\s*\"(.*)\",\\s*\"(.*)\",\\s*\"(.*)\",\\s*\"(Yes|No)\">$");
            matcher = regex.matcher(content);
            boolean edgeFind = false;
            if (matcher.find()) {
                vertices.add(new ArrayList<>(Arrays.asList(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5), matcher.group(6))));
                edgeFind = true;
            }
            regex = Pattern.compile("^HyperEdge\\s*=\\s*<\"(.*)\",\\s*\"(.*)\"(?:,\\s*\\{(.*)})*?>$");
            matcher = regex.matcher(content);
            if (matcher.find()) {
                vertices.add(new ArrayList<>(Arrays.asList(matcher.group(1), matcher.group(2), matcher.group(3))));
            } else if (!edgeFind) {
                throw new FormatException("\"" + content + "\"format error!\n" +
                        "Define Edge format : Edge = <\"Label\",\"type\",\"Weight\",\"StartVertex\",\"EndVertex\",\"Yes|No\">\n" +
                        "Define Hyper Edge format : HyperEdge = <\"Label\",\"Type\",{\"Vertex1\", ..., \"Vertexn\"}>", countLine);
            }
        }
        fileReader.close();
        return vertices;
    }
}
