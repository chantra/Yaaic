/*
Yaaic - Yet Another Android IRC Client

Copyright 2009-2010 Sebastian Kaspari

This file is part of Yaaic.

Yaaic is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Yaaic is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Yaaic.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.yaaic.view;

import java.util.Collection;

import org.yaaic.model.Conversation;
import org.yaaic.model.Server;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * The ConversationSwitcher - The small funny dots at the top ;)
 * 
 * @author Sebastian Kaspari <sebastian@yaaic.org>
 */
public class ConversationSwitcher extends View
{
	private Server server;
	private Paint paint;
	
	/**
	 * Create a new ConversationSwitcher
	 * 
	 * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
	 */
	public ConversationSwitcher(Context context, AttributeSet attributes)
	{
		super(context, attributes);
		
		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
	}
	
	/**
	 * Set the server whos conversations should be displayed
	 * 
	 * @param server
	 */
	public void setServer(Server server)
	{
		this.server = server;
	}
	
	/**
	 * Measure the size of the view
	 * 
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
	 */
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),	16);
	}
	
	/**
	 * On draw
	 */
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
	
		if (server == null) {
			return;
		}

		Collection<Conversation> conversations = server.getConversations();
		
		int i       = 0;
		int width   = getWidth();
		int height  = getHeight();
		int circles = conversations.size();
		int startX  = (width / 2) - (((circles + 1) / 2) * 12);
		
		for (Conversation conversation : conversations) {
			switch (conversation.getStatus()) {
				case Conversation.STATUS_DEFAULT:
					paint.setColor(0xFF888888);
					break;
				case Conversation.STATUS_HIGHLIGHT:
					paint.setColor(0xFF880000);
					break;
				case Conversation.STATUS_MESSAGE:
					paint.setColor(0xFF008800);
					break;
				case Conversation.STATUS_SELECTED:
					paint.setColor(0xFFFFFFFF);
					break;
			}
			
			canvas.drawCircle(startX + 12 * i, height / 2, 4, paint);
			i++;
		}
		
	}
}
