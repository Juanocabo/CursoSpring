package com.bananaapps.bananamusic.persistence.music;

import com.bananaapps.bananamusic.config.SpringConfig;
import com.bananaapps.bananamusic.domain.music.OfflineSong;
import com.bananaapps.bananamusic.domain.music.Song;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@EnableAutoConfiguration
@ActiveProfiles({"prod"})
public class SongInheritanceTest {

	@Autowired
	SongRepository repo;

    @Autowired
	private PlatformTransactionManager transactionManager;		
	
	@Test
	public void testInheritancePositive() {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus transaction = transactionManager.getTransaction(definition);

		Song mi = repo.findOne(1L);
		System.out.println(mi);
		
		assertTrue(mi instanceof OfflineSong);

		Collection<Song> allSongs = repo.findAll();
		allSongs.forEach(i -> System.out.println(i));
        transactionManager.commit(transaction);
	}
}
