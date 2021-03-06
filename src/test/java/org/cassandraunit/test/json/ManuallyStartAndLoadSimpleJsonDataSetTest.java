package org.cassandraunit.test.json;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.factory.HFactory;

import org.cassandraunit.DataLoader;
import org.cassandraunit.dataset.json.ClassPathJsonDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Jeremy Sevellec
 * 
 */
public class ManuallyStartAndLoadSimpleJsonDataSetTest {

	@Before
	public void before() throws Exception {
		EmbeddedCassandraServerHelper.startEmbeddedCassandra();
		DataLoader dataLoader = new DataLoader("TestCluster", "localhost:9171");
		dataLoader.load(new ClassPathJsonDataSet("simpleDataSet.json"));
	}

	@Test
	public void shouldWork() throws Exception {
		String clusterName = "TestCluster";
		String host = "localhost:9771";
		Cluster cluster = HFactory.getOrCreateCluster(clusterName, host);
		Keyspace keyspace = HFactory.createKeyspace("beautifulKeyspaceName", cluster);
		assertThat(keyspace, notNullValue());
		/* and query all what you want */
	}

}
