package crack_code_interview;

public class Demo {

  public static void main(String[] args) {
    System.out.println(Integer.valueOf(Character.MAX_VALUE));
    System.out.println(Character.MAX_CODE_POINT);
    System.out.println(1<<20);
    System.out.println('张');
    
    String val1 = "\u5B66\uD8F0\uDE30";
    
    val1.codePoints().forEach(a -> System.out.println(Character.toChars(a)));
  }

}
