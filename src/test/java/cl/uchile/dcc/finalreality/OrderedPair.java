package cl.uchile.dcc.finalreality;

public class OrderedPair {
  private final double first;
  private final double second;

  public OrderedPair(double a, double b) {
    first = Math.min(a, b);
    second = Math.max(a, b);
  }

  public double getFirst() {
    return first;
  }

  public double getSecond() {
    return second;
  }
}
