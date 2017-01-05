package com.high.food.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource { 
	
	@Override
	protected Object determineCurrentLookupKey() {		
		return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "service_2" : "service";
	}
}
