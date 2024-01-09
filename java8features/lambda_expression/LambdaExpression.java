package lambda_expression;
@FunctionalInterface
interface WithoutParameter{
    void withOutParameter();
}
@FunctionalInterface
interface Demo{
    int show(int i,int j);
}
public class LambdaExpression {
    public static void main(String[] args) {
//      Calling With Parameter without return type
        Demo d = (i,j) -> i+j;
//      Passing The Parameters
        int result = d.show(5,4);
        System.out.println(result);
//      Without parameters
        WithoutParameter wp = ()-> System.out.println("without parameter");
        wp.withOutParameter();
    }
}
