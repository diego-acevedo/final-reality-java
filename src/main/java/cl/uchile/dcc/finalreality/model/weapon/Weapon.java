package cl.uchile.dcc.finalreality.model.weapon;

public interface Weapon {

  String getName();

  int getDamage();

  int getWeight();

  boolean equals(Object o);

  int hashCode();

  String toString();

}
