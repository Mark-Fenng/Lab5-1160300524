package vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Word extends Vertex {

    public Word(String label) {
        super(label);
    }

    @Override
    public void fillVertexInfo(String[] args) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Word && ((Word) obj).getLabel().equals(this.getLabel());
    }

    /**
     * 返回点中的信息
     *
     * @return 包含所有点中信息的字符串
     */
    @Override
    public String getVertexInfo() {
        return "";
    }
}
