package com.aca.web0812.pool;

import java.sql.Connection;

//
abstract public class ConnectionManager {
	public abstract Connection getConnection();
}