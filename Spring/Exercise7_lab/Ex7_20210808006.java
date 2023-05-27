//package Exercise7_lab;

import java.util.ArrayList;
import java.util.List;

//@author S. Murat. ÖZDEMİR
//@since 14.05.2023 - 00:42
public class Ex7_20210808006 {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        
    }
}


interface Damageable {
    void takeDamage(int damage);

    void takeHealing(int healing);

    boolean isAlive();
}

interface Caster {
    void castSpell(Damageable target);

    void learnSpell(Spell spell);
}

interface Combat extends Damageable {
    void attack(Damageable target);

    void lootWeapon(Weapon weapon);
}

interface Useable {
    int use();
}

class Spell implements Useable {
    private int minHeal;
    private int maxHeal;
    private String name;

    public Spell(String name, int minHeal, int maxHeal) {
        this.name = name;
        this.minHeal = minHeal;
        this.maxHeal = maxHeal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int heal() {
        return (int) (Math.random() * (maxHeal - minHeal + 1) + minHeal);
    }

    public int use() {
        return heal();
    }
}

class Weapon implements Useable {
    private int minDamage;
    private int maxDamage;
    private String name;

    public Weapon(String name, int minDamage, int maxDamage) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int attack() {
        return (int) (Math.random() * (maxDamage - minDamage + 1) + minDamage);
    }

    public int use() {
        return attack();
    }
}

class Attributes {
    private int strength;
    private int intelligence;

    public Attributes() {
        this.strength = 3;
        this.intelligence = 3;
    }

    public Attributes(int strength, int intelligence) {
        this.strength = strength;
        this.intelligence = intelligence;
    }

    public void increaseStrength(int amount) {
        strength += amount;
    }

    public void increaseIntelligence(int amount) {
        intelligence += amount;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String toString() {
        return "Attributes [Strength= " + strength + ", intelligence= " + intelligence + "]";
    }
}

abstract class Character implements Comparable<Character> {
    private String name;
    protected int level;
    protected Attributes attributes;
    protected int health;

    public Character(String name, Attributes attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public abstract void levelUp();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " LvL: " + this.level + " - " + this.attributes;
    }

    @Override
    public int compareTo(Character o) {
        return Integer.compare(this.level, o.level);
    }
}

abstract class PlayableCharacter extends Character implements Damageable {
    private boolean inParty;
    private Party party;

    public PlayableCharacter(String name, Attributes attributes) {
        super(name, attributes);
    }

    public boolean isInParty() {
        return inParty;
    }

    public void joinParty(Party party) {
        if (!inParty) {
            try {
                party.addCharacter(this);
                inParty = true;
                this.party = party;
            } catch (PartyLimitReachedException | AlreadyInPartyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void quitParty() throws CharacterIsNotInPartyException {
        if (!inParty) {
            throw new CharacterIsNotInPartyException(this.getName() + " is not in any party.");
        }
        party.removeCharacter(this);
        this.inParty = false;
        this.party = null;
    }
}

abstract class NonPlayableCharacter extends Character {
    public NonPlayableCharacter(String name, Attributes attributes) {
        super(name, attributes);
    }
}

class Merchant extends NonPlayableCharacter {
    public Merchant(String name) {
        super(name, new Attributes(0, 0));
    }

    @Override
    public void levelUp() {
        // empty
    }
}

class Skeleton extends NonPlayableCharacter implements Combat {
    public Skeleton(String name, Attributes attributes) {
        super(name, attributes);
    }

    @Override
    public void attack(Damageable target) {

    }

    public void lootWeapon(Weapon weapon) {
    }

    @Override
    public void levelUp() {
        this.level = getLevel() + 1;
        attributes.increaseStrength(1);
        attributes.increaseIntelligence(1);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public void takeHealing(int healing) {
        health -= healing;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}

class Warrior extends PlayableCharacter implements Combat {
    private Useable weapon;

    public Warrior(String name) {
        super(name, new Attributes(4, 2));
        health = 35;
    }

    @Override
    public void levelUp() {
        attributes.increaseStrength(2);
        attributes.increaseIntelligence(1);
        level++;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public void takeHealing(int healing) {
        health += healing;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void attack(Damageable target) {
        int totalDamage = attributes.getStrength() + weapon.use();
        takeDamage(totalDamage);
    }

    @Override
    public void lootWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}

class Cleric extends PlayableCharacter implements Caster {
    private Useable spell;

    public Cleric(String name) {
        super(name, new Attributes(2, 4));
        health = 25;
    }

    @Override
    public void levelUp() {
        attributes.increaseStrength(1);
        attributes.increaseIntelligence(2);
        level++;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public void takeHealing(int healing) {
        health += healing;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void castSpell(Damageable target) {
        int totalHeal = attributes.getIntelligence() + spell.use();
        target.takeHealing(totalHeal);
    }

    @Override
    public void learnSpell(Spell spell) {
        this.spell = spell;
    }
}

class Paladin extends PlayableCharacter implements Combat, Caster {
    private Useable weapon;
    private Useable spell;

    public Paladin(String name) {
        super(name, new Attributes());
        health = 30;
    }

    @Override
    public void levelUp() {
        if (level % 2 == 1) {
            attributes.increaseStrength(2);
            attributes.increaseIntelligence(1);
            level++;
        } else {
            attributes.increaseStrength(1);
            attributes.increaseIntelligence(2);
            level++;
            level++;
        }
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public void takeHealing(int healing) {
        health += healing;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void castSpell(Damageable target) {
        int totalHeal = attributes.getIntelligence() + spell.use();
        target.takeHealing(totalHeal);
    }

    @Override
    public void learnSpell(Spell spell) {
        this.spell = spell;
    }

    @Override
    public void attack(Damageable target) {
        int totalDamage = attributes.getStrength() + weapon.use();
        takeDamage(totalDamage);
    }

    @Override
    public void lootWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}

class Party {
    private static final int partyLimit = 8;
    private List<Combat> fighters;
    private List<Caster> healers;
    private int mixedCount;

    public Party() {
        fighters = new ArrayList<>();
        healers = new ArrayList<>();
        this.mixedCount = 0;
    }

    public void addCharacter(PlayableCharacter character) throws PartyLimitReachedException, AlreadyInPartyException {
        if (this.fighters.size() + this.healers.size() - mixedCount >= partyLimit) {
            throw new AlreadyInPartyException("Character is already in the party.");
        }

        if (this.fighters.contains(character) || this.healers.contains(character)) {
            throw new PartyLimitReachedException("Party limit has been reached.");
        }

        if (character instanceof Combat) {
            fighters.add((Combat) character);
        }
        if (character instanceof Caster) {
            healers.add((Caster) character);
        }
        if (character instanceof Paladin) {
            mixedCount++;
        }
    }

    public void removeCharacter(PlayableCharacter character) throws CharacterIsNotInPartyException {
        if (!fighters.remove(character) && !healers.remove(character)) {
            throw new CharacterIsNotInPartyException("Character is not in the party.");
        }

        if (character instanceof Combat) {
            fighters.remove((Combat) character);
        }
        if (character instanceof Caster) {
            healers.remove((Caster) character);
        }
        if (character instanceof Paladin) {
            mixedCount--;
        }
    }

    public void partyLevelUp() {
        for (Combat fighter : fighters) {
            if (fighter instanceof Skeleton) {
                Skeleton skeleton = (Skeleton) fighter;
                try {
                    skeleton.levelUp();
                } catch (Exception e) {
                    System.out.println("Error while leveling up Skeleton: " + e.getMessage());
                }
            }
            if (fighter instanceof Warrior) {
                Warrior warrior = (Warrior) fighter;
                try {
                    warrior.levelUp();
                } catch (Exception e) {
                    System.out.println("Error while leveling up Warrior: " + e.getMessage());
                }
            }
            if (fighter instanceof Paladin) {
                Paladin paladin = (Paladin) fighter;
                try {
                    paladin.levelUp();
                } catch (Exception e) {
                    System.out.println("Error while leveling up Paladin: " + e.getMessage());
                }
            }
        }

        for (Caster healer : healers) {
            if (healer instanceof Cleric) {
                Cleric cleric = (Cleric) healer;
                try {
                    cleric.levelUp();
                } catch (Exception e) {
                    System.out.println("Error while leveling up Cleric: " + e.getMessage());
                }
            }
        }
    }

    public String toString() {
        List<Character> allCharacters = new ArrayList<>();

        for (Combat fighter : fighters) {
            allCharacters.add((Character) fighter);
        }

        for (Caster healer : healers) {
            allCharacters.add((Character) healer);
        }

        for (int i = 0; i < allCharacters.size() - 1; i++) { // sort by level
            int minIndex = i;
            for (int j = i + 1; j < allCharacters.size(); j++) {
                if (allCharacters.get(j).getLevel() < allCharacters.get(minIndex).getLevel()) {
                    minIndex = j;
                }
            }
            Character temp = allCharacters.get(i);
            allCharacters.set(i, allCharacters.get(minIndex));
            allCharacters.set(minIndex, temp);
        }

        //remove duplicates
        try {
            for (int i = 0; i < allCharacters.size() - 1; i++) {
                if (allCharacters.get(i).equals(allCharacters.get(i + 1))) {
                    allCharacters.remove(i);
                }
            }
        } catch (Exception e) {// catch index out of bounds

        }

        StringBuilder sb = new StringBuilder();
        for (Character character : allCharacters) {
            sb.append(character.toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}

class Barrel implements Damageable {
    private int health = 30;
    private int capacity = 10;


    public void explode() {
        System.out.println("Explodes");
    }

    public void repair() {
        System.out.println("Repairing");
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            explode();
        }
    }

    public void takeHealing(int healing) {
        health += healing;
        repair();
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}

class TrainingDummy implements Damageable {
    private int health = 25;

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public void takeHealing(int healing) {
        health += healing;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}

class PartyLimitReachedException extends Exception {
    private String message;

    public PartyLimitReachedException(String message) {
        super(message);
    }
}

class AlreadyInPartyException extends Exception {
    private String message;

    public AlreadyInPartyException(String message) {
        super(message);
    }
}

class CharacterIsNotInPartyException extends Exception {
    private String message;

    public CharacterIsNotInPartyException(String message) {
        super(message);
    }
}