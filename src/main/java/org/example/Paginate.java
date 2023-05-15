import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Paginate {

    public static List<Integer> getPage(List<Integer> sourceList, int page, int pageSize) {
        if (page * pageSize > sourceList.size())
            return sourceList;
        int maxBound = (page + 1) * pageSize;
        if (maxBound > sourceList.size()) {
            maxBound = page * pageSize + (sourceList.size() % (page * pageSize));
        }
        return sourceList.subList(page * pageSize, maxBound);
    }

    public static List<Integer> getPage2(List<Integer> sourceList, int page, int pageSize) {

        if(pageSize <= 0 || page < 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = page * pageSize;
        if(sourceList == null || sourceList.size() < fromIndex){
            return Collections.emptyList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

    public static void main(String[] args) {
        List<Integer> global = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> a = getPage(global, 0, 2);
        List<Integer> b = getPage2(global, 1, 5);
        List<Integer> c = getPage2(global, 0, 5);
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());
    }
}
