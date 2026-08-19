package numberrangesummarizer;

import java.util.Collection;

public class SortedRangeSummarizer extends GeneralRangeSummarizer{

    @Override
    public Collection<Integer> collect(String input) {
        return super.collect(input).stream().sorted().toList();
    }

}
