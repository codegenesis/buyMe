package com.couchbase.dataaccess;

/**
 * The interface needs to be implemented for data access.
 *
 */
public interface DataAccessAPI
{

   public String save(String object) throws Exception;

   public void delete(String documentId) throws Exception;

   /**
    * @return
    * @throws Exception
    */
   public Long getAutoIncrementedValue() throws Exception;

   /**
    * Method to fetch the results from the database based on the unique id.
    *
    * @param id
    * @return String
    * @throws Exception
    */
   public String getFilteredResultsById(String id) throws Exception;

}
