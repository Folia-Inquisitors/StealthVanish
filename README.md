# Official Discord

https://discord.gg/aT9z7q7hX8

## Building instructions

mvn clean install
 
## Description

This is a vanishing plugin that lets staff stay hidden. This plugin is free and open source, feel free to contribute. 

## features / selling points

- Designed to be version stable for future updates.

- Regex to hide usernames from apparing / username leak protection support preventing hidden staff from being shown on other plugins

- Similar API calls to other vanishing plugins ensuring compatability without plugins needing to add support.

- More modernized version with lots of notes to make code understanable

- Designed from the ground up to support folia

## Commands 
```
/stealth
/stealth toggle
/stealth on
/stealth off
/stealth status
/stealth capabilities
/stealth <on|off|toggle|status> <player>
```

Permission Nodes

```
stealthvanish.command
Allows using /stealth for yourself. Default: op.

stealthvanish.command.others
Allows using /stealth ... <player> to check or change someone else’s vanish state. Default: op.

stealthvanish.see
Admin/bypass permission. Lets someone see vanished players, see real join/quit state, see vanished players in tab-complete/player-name surfaces, and bypass the respect command guard. Default: op.
```

## API
```
Other plugins can use:

VanishAPI.isInvisible(player);
VanishAPI.isInvisible(uuid);
VanishAPI.isInvisible(playerName);

VanishAPI.hidePlayer(player);
VanishAPI.showPlayer(player);

VanishAPI.canSee(viewer, target);
VanishAPI.shouldHideFrom(viewer, target);
VanishAPI.shouldHideNameFrom(viewer, playerName);
```

## Disclaimer

Ai was used when helping make this plugin during testing and commits.

# Folia inquisitors

[<img src="https://github.com/Folia-Inquisitors.png" width=80 alt="Folia-Inquisitors">](https://github.com/orgs/Folia-Inquisitors/repositories)
[<img src="https://github.com/Yomamaeatstoes.png" width=80 alt="Yomamaeatstoes">](https://github.com/Yomamaeatstoes)
[<img src="https://github.com/HSGamer.png" width=80 alt="HSGamer">](https://github.com/HSGamer)
