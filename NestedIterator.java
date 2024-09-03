/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    private List<Integer> flatList;
    private int index;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        flatList = new ArrayList<>();
        flattenList(nestedList);
        index = 0;
    }
    
    // Recursive function to flatten the nested list
    private void flattenList(List<NestedInteger> nestedList) {
        for (NestedInteger nested : nestedList) {
            if (nested.isInteger()) {
                flatList.add(nested.getInteger());
            } else {
                flattenList(nested.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return flatList.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < flatList.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
