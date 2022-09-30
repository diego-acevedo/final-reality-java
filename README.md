Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

Game Units
-------

This game consists of two units: characters and weapons.

### Characters

There are 6 types of characters:
- Knight
- Engineer
- Thief
- Black Mage
- White Mage 
- Enemy

The first five characters can be used by the user. Unlike enemies,
this characters can equip weapons. The Black Mage character and White
Mage character correspond to a subtype of playable characters; they
can cast black magic and white magic, respectively.

Enemies cannot be played by the user, nor they can equip any weapons.
They are the only types of enemies who have defined a weight atribute.

#### Attack mechanics

Each character has a cooldown when they attack. The cooldown's waiting
time is defined by:

- the weapon's weight, if it's a playable character,
- the character's weight, if it's an enemy.

### Weapons

There are five types of weapons:

- Sword
- Bow
- Axe
- Knife
- Staff

Each weapon has their own weight (which is used for the cooldown).
However, the Staff has an extra atribute: magic damage. This is the
only weapon in the game that can be used to cast spells.

Changes implemented (Firts Assigment)
-------

### Weapons

1. Change type implementation from Enumeration to Classes
and Abstract Classes (polimorphism).
2. Add interface Weapon to be used as a type for all weapons.
3. Add abstract class AbstractMagicWeapon and interface 
MagicWeapon. For all the weapons implemented this far, this 
abstract class isn't a good implementation, but it is good if
more weapons are expected to be added, especially, magic weapons.

### Characters

1. Add abstract class AbstractMage to hold all the common
characteristics between mages. BlackMage and WhiteMage are
extended from this new class.
2. Move waitTurn method from AbstractCharacter to
AbstractPlayerCharacter and Enemy. This allows the code not
to implement instanceof in the waitTurn method. Each class
knows how to behave when the method is called.
