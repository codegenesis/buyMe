package com.couchbase.dataaccess.couchbase.server;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.dataaccess.AbstractDataAccessAPI;
import com.couchbase.dataaccess.factory.DataAccessFactory.SupporedDatabase;

/**
 * Class for all the Couchbase database operations.
 *
 */
@Service
public class CouchbaseServerDataAccessImpl extends AbstractDataAccessAPI
{

	@Autowired
	private Bucket couchbaseServerBucket;

	static final String COUCHBASE_SERVER_BUCKET_NAME = "user";

	@Override
	public String save(String object) throws Exception
	{

		JSONObject jsonObject = new JSONObject(object);
		String documentId = jsonObject.getString("id");
		JsonDocument doc = JsonDocument.create(documentId, JsonObject.fromJson(object));
		couchbaseServerBucket.upsert(doc);
		String id = getFilteredResultsById(documentId);
		System.out.println(id);
		// fetching inserted document
		return id;
	}

	@Override
	public SupporedDatabase type()
	{
		return SupporedDatabase.COUCHBASESERVER;
	}

	@Override
	public void delete(String documentId) throws Exception
	{
		couchbaseServerBucket.remove(documentId);
	}

	@Override
	public Long getAutoIncrementedValue() throws Exception
	{
		couchbaseServerBucket.counter("idGenerator", 0, 0);
		return couchbaseServerBucket.counter("idGenerator", 1).content();
	}

	@Override
	public String getFilteredResultsById(String id) throws Exception
	{
		// Query execution by N1QL query
		/*
		 * long startTime = System.currentTimeMillis(); StringBuilder query =
		 * new StringBuilder("SELECT * FROM");
		 * query.append(" ").append(COUCHBASE_SERVER_BUCKET_NAME).
		 * append(" WHERE id = '").append(id).append("'"); N1qlQueryResult
		 * queryResult =
		 * couchbaseServerBucket.query(N1qlQuery.simple(query.toString())); long
		 * endTime = System.currentTimeMillis(); System.out.println(endTime -
		 * startTime); for(N1qlQueryRow queryRow : queryResult) { JsonObject
		 * jsonObject =
		 * (JsonObject)queryRow.value().get(COUCHBASE_SERVER_BUCKET_NAME);
		 * return jsonObject.toString(); } return "";
		 */


		// Query execution by get method
		long startTime = System.currentTimeMillis();
		JsonDocument jsonDocument = couchbaseServerBucket.get(id);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		return jsonDocument.content().toString();
	}

}
