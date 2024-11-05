# Design Document for "Mod Name TBD"

## Dependencies

- Fabric Loader 0.16.9
- Fabric API 0.92.2+1.20.1
- Minecraft 1.20.1
- Cloth Config 11.1.106
- Better Combat 1.8.4+1.20.1-fabric
- playerAnimator 1.0.2-rc1+1.20

## Content Overview

This mod modifies the same systems as the mods "Food Overhaul" and "Overhauled Damage" do. It is therefor not compatible with those two mods.

### Food Overhaul

The vanilla hunger system, including exhaustion & saturation is disabled. Food items are changed to grant health instead of hunger with the following calculation:

> health = Math.min((hunger / 2), 1)

#### Fullness system

Eating food items also grants 1 point of fullness.
When fullness >= 3, every food item grants 1 health and 0 fullness, instead of the normal values.

> Suggestion for an alternative: prevent eating, so players don't waste good food & send message to player "Your stomach is full"

Fullness is reduced by 1 every n(controlled by server config) seconds.

### Attack Overhaul

> Damage types are a vanilla concept, that are used to determine damage immunities, damage vulnerabilities, death messages, etc.

> "Better Combat Expansion" adds a "damage_type" field to "Better Combat"s weapon_attribute files. When this field is a valid damage type identifier, that damage type replaces the "minecraft:player_attack" damage type for that attack. This mod can use that addition and therefor does not need an additional field in the weapon_attribute file. "Overhauled Damage" works similar.

#### Attack Types

To avoid confusion, I call the new system "Attack Types" instead of "Damage Types".
Attack types are an optional property of (player) attacks. They are determined by the damage type of the attack.

- Trauma Attack, dealt by attacks with the "player_attack_trauma" damage type. Attacks have a 20% chance to be a critical hit.

> Critical hits are a vanilla mechanic, that multiplies player attack damage by 1.5 when the player is falling during the attack.

- Stab Attack, dealt by attacks with the "player_attack_stab" damage type. Attacks have a 20% chance to ignore armor.

- Slash Attack, dealt by attacks with the "player_attack_slash" damage type. Attacks have a 20% chance to apply a status effect to the target. The status effect is specified in the server config. If the target has the status effect already, the effect duration is reset.

#### Stance

Stance is a resource, similar to health. It can be increased, decreased and has a maximum value. Its current value is not visible to the player.

Attacks in the weapon_attribute file of "Better Combat" get an additional optional int field, called "poise_damage". It defaults to 0.

Attacks subtract the specified poise damage from the targets stance. When an attack is a crit attack (either y jumping or from a Trauma attack), the poise damage is multiplied by 2.

Stance increases by n (set in the server config) every 20 ticks (1 second), after not getting decreased for 60 ticks (3 seconds).

Players also receive poise damage when their attacks are blocked. The amount is configured in the server config.

When players stance is above 20 (not sure if you maybe mean below?), stance is also increased by successful attacks (not blocked) to exactly 20.

#### Armor

This is a system, where the total amount of armor a player currently has influences the maximum value of stance and stamina (added by "Stamina Attributes").

The following segment describes the exact calculation in pseudo code, to avoid misunderstandings

```
var armor = player.getArmor()
var maxStamina = player.getMaxStamina()
var maxStance = player.getMaxStance()

if (armor < server_config.armor_threshold_1) {

  maxStamina = maxStamina + server_config.stamina_modifier_1

  maxStance = maxStance + server_config.stance_modifier_1

} else if (armor > server_config.armor_threshold_2) {

  maxStamina = maxStamina + server_config.stamina_modifier_2

  maxStance = maxStance + server_config.stance_modifier_2

} else if (armor > server_config.armor_threshold_3) {

  maxStamina = maxStamina + server_config.stamina_modifier_3

  maxStance = maxStance + server_config.stance_modifier_3

}

player.setMaxStamina(maxStamina);
player.setMaxStance(maxStance);
```

How exactly the changes to maxStamina and maxStance are applied, is a design question.

My recommendation would be to use status effects. The check described above would be made every n ticks. When one if the throsholds is met by the armor value a status effect would be applied for n + x ticks. This status effect would have apply the two required attribute modifiers. It would also serve as a way to tell the player that having this much/little armor has consequences.