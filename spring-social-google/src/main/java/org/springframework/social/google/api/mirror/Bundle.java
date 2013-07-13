package org.springframework.social.google.api.mirror;

import java.util.*;

/**
 * The representation of an aggregate of cards.
 *
 * @author Josh Long
 */
public class Bundle extends TimelineItem {



	private LinkedList<Card> cardLinkedList = new LinkedList<Card>();

	public void addCard(Card card) {
		this.cardLinkedList.add(card);
	}

	public List<Card> getCards() {
		return new ArrayList<Card>(this.cardLinkedList);
	}
}
