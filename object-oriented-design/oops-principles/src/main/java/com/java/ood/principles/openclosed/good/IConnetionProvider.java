package com.java.ood.principles.openclosed.good;

import java.sql.Connection;

public interface IConnetionProvider {
	public Connection establishconnection();
}
