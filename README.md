# Healthy Food

Eating food no longer fills the hunger bar or gives saturation. Each food item now heals the player directly.

Eating food will add fullness, when the maximum fullness is reached, no food can be consumed. Fullness is decreased over time.

Maximum fullness is controlled by the "healthyfood:generic.maximum_fullness" entity attribute, with a default of 3.

Food adds 1 fullness by default, exceptions can be added via the server config.

Eating food will heal its previous saturation / 2, but at least 1 health or half a heart.

This ensured that food items added by other mods are compatible out of the box.
