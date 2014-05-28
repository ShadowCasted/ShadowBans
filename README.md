ShadowBans
Changelog 5/28/14:
- Added Silent and Broadcasted ban
- Fixed plugin.yml
- Fixed banned.yml
- BUGGED: Temp-ban only kicks the player for “:” time
- BUGGED: Making it a silent ban will have “-s” in the ban reason

Changelog 5/9/14:
- Adapted everything to UUIDs.
- Added :
- Temp-banning
- Getting the UUID of a player

Changelog 5/2/14:
- Created code for broadcasted or silent bans, need to be tested before added into the src.

Changelog 4/30/14:

Added:
- Banning
- Kicking
- Unbanning
- Check if a player is banned

A project by the ShadowCasted team:

- This banning plugin takes advantage of the 1.7.9 UUID.
Features:
- Banning √
- Unbanning √
- *Kicking
- Temporary banning 
- Writing the user's UUID to the config, not just their username√
- Able to check bans with an in game command √
- *Broadcasted or silent ban, whether only the person banning can only see the message or everyone on the server can √
(Maybe even give a way so that a few people could see the message with a certain permission) √
Config options:
- *Ban broadcasted, whether the ban is allowed to be broadcasted or not, (if this is false, all bans will be silent) (implemented in game) √
- *Write to Bukkit, will backup the bans from this plugin with the bukkit's banned-players.yml
- *When a player joins, the config writes the user's name into the config, with options under the name such as "banned" which 
will be false by default. 
- *IP Address of player
- More to come

 * = Not done yet
