# **Keep-On-Track Game**
This is a mobile Java game created on Eclipse using Codename One for a class at Sacramento State. The goal of this project was to apply object-oriented principles, practice with interactive graphics and animation, and get introduced to mobile app development.

**Goal of the game:** As a player, you control a player cyborg, shown as a solid red square. Your goal is to touch each base (solid blue triangle) in sequential order as fast as possible. As you play, the energy level of your cyborg slowly decreases, since it takes energy to power your cyborg, but this could be replenished by visiting energy stations around the map (green circles). To make things harder, you are also racing against NPC cyborgs (red square outline), where some are trying to attack you, while others try to complete the race before you, as well as drones (black triangle outline) that fly around doing damage.

**Controls:** When you play, you control your cyborg using the buttons on the UI, as well as the related keys that are shown under "help." You are able to steer your cyborg left and right, increase speed, which is proportional to your damage level (50% damage = 50% of max speed), and decrease speed. You can also control other aspects of the game, such as play/pause, turning on & off sound effects, re-positioning stationary objects (energy stations and bases), and changing the strategies of the NPC cyborgs. When you change strategies, the cyborgs that are attacking you switch to completing the race, and vice-versa. To re-position stationary game objects, you must 1) pause the game, 2) select a stationary object, 3) press position, and 4) press somewhere on the game map, which will move that object to that location.


# **Run Game**
In order to properly run this game, you must have JRE 8 and Codename One downloaded onto your computer. The game was developed for an iPad, so the skin MUST be "ipad3_os7.skin."

**Eclipse:** after importing the project to eclipse, right click on "Simulator_A3Prj.launch" --> Run As --> Simulator_A3Prj

**MacOS: **from terminal, change directories to "A3Prj" and run this command: java -cp dist/A3Prj.jar:JavaSE.jar com.codename1.impl.javase.Simulator com.mycompany.a3.Starter

**Windows:** from command prompt, change directories to "A3Prj" and run this command: java -cp dist\A3Prj.jar;JavaSE.jar com.codename1.impl.javase.Simulator com.mycompany.a3.Starter

# **Gameplay**
Quick Demo:
![gameplay](https://user-images.githubusercontent.com/63819319/119928259-11a08500-bf30-11eb-896d-aa2010e12928.gif)

Demo of some functionality:
![gameplay2](https://user-images.githubusercontent.com/63819319/119928765-14e84080-bf31-11eb-8150-291c55e5d3ac.gif)

