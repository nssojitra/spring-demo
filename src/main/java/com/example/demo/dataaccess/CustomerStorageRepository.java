package com.example.demo.dataaccess;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableClientBuilder;
import com.azure.data.tables.models.TableEntity;
import com.example.demo.models.Customer;

@Component
public class CustomerStorageRepository {


	private final String _connstr="DefaultEndpointsProtocol=https;AccountName=testnayan1;AccountKey=Bh8t7dUAC+XN6hV7Me6fafktB+iMCWgDhsbDwF0cm0OHj0UBy35VNI1NlRMovn0wuN3iBl3HAX/FI25fIPMuzA==;EndpointSuffix=core.windows.net";


	private TableClient GetTableClient(String tableName)
	{
		return  new TableClientBuilder()
				.connectionString(_connstr)
				.tableName(tableName)
				.buildClient();
	}

	public Customer Save(Customer customer)
	{
		var tableClient = GetTableClient("cust");

		if(tableClient!=null)
		{


			var entity = new TableEntity(customer.getLastName(),customer.getFirstName())
					.addProperty("age", customer.getAge())
					.addProperty("income", customer.getIncome());

			tableClient.upsertEntity(entity);
		}
		return customer;
	}
	
	public List<Customer> GetAll()
	{
		List<Customer> ret = new ArrayList<Customer>();
		var tableClient = GetTableClient("cust");

		if(tableClient!=null)
		{
			tableClient.listEntities().forEach(c->{
				var current = new Customer();
				current.setLastName(c.getPartitionKey());
				current.setFirstName(c.getRowKey());
				current.setIncome((double)c.getProperty("income"));
				current.setAge((int)c.getProperty("age"));
				ret.add(current);
			});
			
		}
		return ret;
	}
	
	public void Delete(String firstName, String lastName)
	{
		var tableClient = GetTableClient("cust");

		if(tableClient!=null)
		{
			tableClient.deleteEntity(lastName,firstName);
		}
	}
}
