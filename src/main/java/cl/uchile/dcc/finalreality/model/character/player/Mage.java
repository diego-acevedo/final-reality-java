package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.spell.Spell;

public interface Mage extends PlayerCharacter {

  void conjureSpell(Spell spell, GameCharacter character);
}
