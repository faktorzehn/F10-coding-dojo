package controller;

public interface Bot {

	/**
	 * Announce the move you are going to play. You don't actually have to play it,
	 * but being honest is rewarded.
	 */
	public Move announce();

	/**
	 * Select the move you are actually going to play. The move your opponent announced is given as a parameter. 
	 */
	public Move play(Move enemyAnnouncement);

}
