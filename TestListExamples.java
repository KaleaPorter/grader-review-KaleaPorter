import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;

class IsA implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("a");
  }
}

public class TestListExamples {
  @Test(timeout = 500) 
  public void testFilter(){
    List<String> s1 = Arrays.asList("a", "b", "c");
    List<String> s2 = Arrays.asList("d", "a");

    List<String> expect = Arrays.asList("a");

    List<String> result1 = ListExamples.filter(s1, new IsA());
    List<String> result2 = ListExamples.filter(s2, new IsA());
    assertEquals(expect, result1);
    assertEquals(expect, result2);
  }

  @Test(timeout = 500) 
  public void testFilter2(){
    List<String> s1 = Arrays.asList("a", "b", "c");
    List<String> s2 = Arrays.asList("d", "a", "a");

    List<String> expect1 = Arrays.asList("a");
    List<String> expect2 = Arrays.asList("a", "a");

    List<String> result1 = ListExamples.filter(s1, new IsA());
    List<String> result2 = ListExamples.filter(s2, new IsA());

    assertEquals(expect1, result1);
    assertEquals(expect2, result2);
  }


  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
    assertEquals(expected.size(), merged.size());
  }

  @Test(timeout = 500)
  public void testMerge2() {
    List<String> left = Arrays.asList("g", "j", "p");
    List<String> right = Arrays.asList("b", "h", "r");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("b", "g", "h", "j", "p", "r");
    assertEquals(expected, merged);
    assertEquals(expected.size(), merged.size());

  }

  @Test(timeout = 500)
  public void testMerge3() {
    List<String> left = Arrays.asList("apple", "banana", "grapefruit");
    List<String> right = Arrays.asList("ableton", "band", "music");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("ableton", "apple", "banana", 
    "band", "grapefruit", "music");
    assertEquals(expected, merged);
    assertEquals(expected.size(), merged.size());
  }

  @Test(timeout = 500)
  public void testMerge1Empty() {
    List<String> left = Arrays.asList();
    List<String> right = Arrays.asList("a", "b", "c");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "b", "c");
    assertEquals(expected, merged);
    assertEquals(expected.size(), merged.size());
  }

  @Test(timeout = 500)
  public void testMergeBothEmpty() {
    List<String> left = Arrays.asList();
    List<String> right = Arrays.asList();
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList();
    assertEquals(expected, merged);
    assertEquals(0, merged.size());
  }

}

