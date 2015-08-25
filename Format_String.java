class Format_String{

  public static void main(String[] args){
    String fs;

    float floatVar = 32.3f;
    int intVar = 365;
    String stringVar = "MyString";
    fs = String.format("The value of the float variable is " +
                   "%f, while the value of the integer " +
                   "variable is %d, and the string " +
                   "is %s", floatVar, intVar, stringVar);
    System.out.println(fs);
  }
}

