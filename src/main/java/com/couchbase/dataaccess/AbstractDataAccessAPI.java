package com.couchbase.dataaccess;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.couchbase.dataaccess.factory.DataAccessFactory;
import com.couchbase.dataaccess.factory.DataAccessFactory.SupporedDatabase;

/**
 * The abstract class will be used for data access.
 *
 */
public abstract class AbstractDataAccessAPI implements DataAccessAPI
{

   @Autowired
   DataAccessFactory dataAccessFactory;

   /**
    * @return SupporedDatabase
    */
   public abstract SupporedDatabase type();

   /**
    * @return void
    */
   @PostConstruct
   public void registerBean()
   {
      dataAccessFactory.register(type(), this);
   }
}
