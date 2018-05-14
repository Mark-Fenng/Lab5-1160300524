package factory.graph;

import factory.Exception.FormatException;
import factory.Exception.TypeException;
import graph.Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

abstract public class GraphFactory {
    public static Graph createGraph(String filePath) throws IOException, TypeException, FormatException {
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        String content;
        while ((content = fileReader.readLine()).replace(" ", "").equals("")) ;
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
                    throw new TypeException("Definite Graph type format : GraphType=\"GraphPoet\"|\"SocialNetwork\"|\"NetworkTopology\"|\"MovieGraph\"");
            }
        } else {
            throw new FormatException("Miss the Graph Type");
        }
    }

    static String GraphLabel(String filePath) throws IOException, FormatException {
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        Pattern regex;
        Matcher matcher;
        String content;
        fileReader.readLine();  // graph type
        // graph name
        if ((content = fileReader.readLine()) != null) {
            fileReader.close();
            regex = Pattern.compile("GraphName\\s*=\\s*\"(.*)\"");
            matcher = regex.matcher(content);
            if (matcher.find()) {
                return matcher.group(1);
            } else {
                throw new FormatException("Definite Graph label format : GraphName=\"name\"");
            }
        } else {
            throw new FormatException("Miss the Graph Label");
        }
    }

    public static List<List<String>> getVertices(String filePath) throws IOException, FormatException {
        List<List<String>> vertices = new ArrayList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        Pattern regex;
        Matcher matcher;
        String content;
        // 跳过图的类型，图的label的内容
        while (!fileReader.readLine().equals("")) ;
        content = fileReader.readLine();
        regex = Pattern.compile("^VertexType\\s*=\\s(\\w+)$");
        matcher = regex.matcher(content);
        if (!matcher.find()) {
            throw new FormatException("Miss the Vertex Type");
        }
        // 找到文件中关于点的描述
        while (!(content = fileReader.readLine()).equals("")) {
            regex = Pattern.compile("^Vertex\\s*=\\s*<\"(.*)\",\\s*\"(.*)\"(?:,\\s*<(.*)>)?>$");
            matcher = regex.matcher(content);
            if (matcher.find()) {
                String label = matcher.group(1);
                String type = matcher.group(2);
                String attr = matcher.group(3);
                vertices.add(new ArrayList<>(Arrays.asList(label, type, attr)));
            } else {
                throw new FormatException("\"Content\" format error!\nDefinite Vertex format : Vertex = <\"Label1\",\"type1\",<\"attr1\",...,\"attrk\">>");
            }
        }
        fileReader.close();
        return vertices;
    }

    public static List<List<String>> getEdges(String filePath) throws IOException, FormatException {
        List<List<String>> vertices = new ArrayList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        Pattern regex;
        Matcher matcher;
        String content;
        // 跳过图的类型，图的label的内容
        while (!fileReader.readLine().equals("")) ;
        // 跳过点的描述信息
        while (!fileReader.readLine().equals("")) ;
        content = fileReader.readLine();
        regex = Pattern.compile("^EdgeType\\s*=\\s(\\w+)$");
        matcher = regex.matcher(content);
        if (!matcher.find()) {
            throw new FormatException("Miss the Edge Type");
        }
        // 找到文件中关于点的描述
        while ((content = fileReader.readLine()) != null && !content.equals("")) {
            regex = Pattern.compile("^Edge\\s*=\\s*<\"(.*)\",\\s*\"(.*)\",\\s*\"(.*)\",\\s*\"(.*)\",\\s*\"(.*)\",\\s*\"(.*)\">$");
            matcher = regex.matcher(content);
            if (matcher.find()) {
                vertices.add(new ArrayList<>(Arrays.asList(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5), matcher.group(6))));
            }
            regex = Pattern.compile("^HyperEdge\\s*=\\s*<\"(.*)\",\\s*\"(.*)\"(?:,\\s*\\{(.*)})*?>$");
            matcher = regex.matcher(content);
            if (matcher.find()) {
                vertices.add(new ArrayList<>(Arrays.asList(matcher.group(1), matcher.group(2), matcher.group(3))));
            } else{
                throw new FormatException("\"Content\" format error!\nDefinite Vertex format : Vertex = <\"Label1\",\"type1\",<\"attr1\",...,\"attrk\">>");
            }
        }
        fileReader.close();
        return vertices;
    }
}
