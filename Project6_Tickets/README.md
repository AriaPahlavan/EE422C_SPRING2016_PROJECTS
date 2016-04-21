# Assignment6

Aria Pahlavan -- ap44342
Jett Anderson -- jra2995


Comments: 
>> A threatre is devided up into three different house: 1) Middle house. 2) House left. 3) House right.
	therefore a Theatre objects contains three TheatreHouse objects, each of which has a sorted list of seats from left to right (left beign the 		best seat in that particular house). bestAvailableSeat() method will pick the first (best) seat from each arraylist (middle, house left, and 		house right) and then pick the best seat among those three (i.e. the front row is better than middle and back rows, AND the middle seats are 		better than the house left seats and house right seats).

>> Github link: github.com/AriaPahlavan/Assignment6/      -- Will add Mehtaab to the list
>> Master branch is the final version of our project

>> Design: So our design encompasses the ability to have multiple box offices, represented by TicketServer objects (actual objects that call their own start method, removing the necessity to call it from a test method). TicketServers must be constructed with port number first (preferably 16790, 16791, 16792 if multiple servers with a queue of randomized clients are being created), followed by Box Office name as a string. TicketClients, representing clients that will "purchase" a single ticket, must be created either with the name of the client as a string as the sole argument, which will then select one of the three random port numbers listed above as the port number to connect to -- or with a string name and an int for the two arguments, the int representing the hard coded port number that must match one of the server numbers selected (NOTE: if this method is used to create TicketClients, you don't have to use 16790, 16791, 16792, BUT the port for a created TicketServer and the created TicketClient MUST MUST MUST match). So the TicketServer represents a Box Office, and the TicketClient represents a single client requesting a ticket from the server. Again, if the TicketClient is being hardcoded with a port number, it must match a hardcoded Server number and it cannot be used in a random fashion, ie it's going to request a ticket from that corresponding server. See RandomizedPortTest for a queue of random clients with the three servers with the 16790, 16791, and 16792 ports as designated.
