package factory.graph;

import Exception.Edge.*;
import Exception.FormatException;
import Exception.TypeException;
import Exception.Vertex.VertexAttributeException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import factory.edge.EdgeFactory;
import graph.Graph;
import LoggerFactory.*;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

abstract public class GraphFactory {
    public static Graph createGraph(String filePath) throws IOException, TypeException, FormatException, EdgeNullVertexException, VertexAttributeException, VertexTypeException, EdgeTypeException, DirectedEdgeException, HyperEdgeException, EdgeWeightException, VertexLabelException {
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        String content;
        int countLine = 1; // 统计文件的行数，便于报错
        while ((content = fileReader.readLine()) != null) {
            if (!content.replace(" ", "").equals("")) {
                break;
            }
            countLine++;
        }
        if (content == null)
            throw new IOException();
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
        long startTime = System.currentTimeMillis();
        List<List<String>> vertices = new ArrayList<>();
        BufferedReader fileReader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8);
        Pattern regex;
        Matcher matcher;
        String content;
        int countLine = 0; // 统计文件的行数，便于报错
        String temp;
        // 跳过图的类型，图的label的内容
        do {
            countLine++;
            temp = fileReader.readLine();
        } while (!(temp != null && temp.equals("")));
        content = fileReader.readLine();
        countLine++;
        regex = Pattern.compile("^VertexType\\s*=\\s*\"(.*)\"$");
        matcher = regex.matcher(content);
        if (!matcher.find()) {
            throw new FormatException("Miss the Vertex Type", countLine);
        }
        // 找到文件中关于点的描述
        while ((content = fileReader.readLine()) != null && !content.equals("")) {
            countLine++;
            regex = Pattern.compile("^Vertex\\s*=\\s*<\"(\\w+)\",\\s*\"(.*)\"(?:,\\s*<(.*)>)?>$");
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
        long endTime = System.currentTimeMillis();
        System.out.println("Read vertices finished  Using " + (endTime - startTime) / 1000.0 + " s");
        fileReader.close();
        return vertices;
    }

    /**
     * 从包含图信息的固定格式文件中单独获取图中点的信息
     *
     * @param filePath 包含图信息的固定格式文件的路径
     * @return 包含图信息的固定格式文件读取的结果 第一层列表存储所有的边，第二层列表存储一条边的全部信息
     * 第二层列表中存储的内容次序为  label,type,weight,vertices,yes|no
     * @throws IOException           包含图信息的固定格式文件读取的异常
     * @throws FormatException       传入的文件的格式不符合要求异常，处理异常时要求给出异常的提示信息，并允许用户重新读入新的的文件，
     * @throws DirectedEdgeException 当边的类型是有向边，若文件的描述是无向边，则抛出此异常
     */
    public static List<List<String>> getEdges(String filePath) throws IOException, FormatException, DirectedEdgeException {
        long startTime = System.currentTimeMillis();
        List<List<String>> vertices = new ArrayList<>();
        BufferedReader fileReader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8);
//        FileInputStream fin = new FileInputStream("readandshow.txt");
//        FileChannel fc = fin.getChannel();
//        fc.read(buffer);
        Pattern regex;
        Matcher matcher;
        String content;
        int countLine = 0; // 统计文件的行数，便于报错
        // 跳过图的类型，图的label的内容
        String temp;
        do {
            countLine++;
            temp = fileReader.readLine();
        } while (!(temp != null && temp.equals("")));
        // 跳过点的描述信息
        do {
            countLine++;
            temp = fileReader.readLine();
        } while (!(temp != null && temp.equals("")));
        content = fileReader.readLine();
        countLine++;
        regex = Pattern.compile("^EdgeType\\s*=\\s*\"(.*)\"$");
        matcher = regex.matcher(content);
        if (!matcher.find()) {
            throw new FormatException("Miss the Edge Type", countLine);
        }
        // 找到文件中关于点的描述
        while ((content = fileReader.readLine()) != null && !content.equals("")) {
            countLine++;
            regex = Pattern.compile("^Edge\\s*=\\s*<\"(\\w+)\",\\s*\"(.*)\",\\s*\"(-?[0-9]+\\.?[0-9]*)\",\\s*\"(.*)\",\\s*\"(.*)\",\\s*\"(Yes|No)\">$");
            matcher = regex.matcher(content);
            boolean edgeFind = false;
            if (matcher.find()) {
                vertices.add(new ArrayList<>(Arrays.asList(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5), matcher.group(6))));
                try {
                    // 当返回值是false时，表明文件中读入的边是否是有向边的信息是错误的
                    if (!EdgeFactory.edgeType(matcher.group(1), matcher.group(2), matcher.group(6).equals("Yes")))
                        throw new UndirectedEdgeException(matcher.group(1), countLine); // 无向边中出现了有向边的定义，只是发出警告，不停止运行
                } catch (DirectedEdgeException e) {
                    throw new DirectedEdgeException(matcher.group(1), countLine);
                } catch (UndirectedEdgeException e) {
                    MyLogger.warning(e.toString() + "\ngo on read the file");
                }
                edgeFind = true;
            }
            regex = Pattern.compile("^HyperEdge\\s*=\\s*<\"(\\w+)\",\\s*\"(.*)\"(?:,\\s*\\{(.*)})*?>$");
            matcher = regex.matcher(content);
            if (matcher.find()) {
                vertices.add(new ArrayList<>(Arrays.asList(matcher.group(1), matcher.group(2), matcher.group(3))));
            } else if (!edgeFind) {
                throw new FormatException("\"" + content + "\"format error!\n"
                        + "Define Edge format : Edge = <\"Label\",\"type\",\"Weight\",\"StartVertex\",\"EndVertex\",\"Yes|No\">\n"
                        + "Define Hyper Edge format : HyperEdge = <\"Label\",\"Type\",{\"Vertex1\", ..., \"Vertexn\"}>", countLine);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Read edges finished  Using " + (endTime - startTime) / 1000.0 + " s");
        fileReader.close();
        return vertices;
    }
}
