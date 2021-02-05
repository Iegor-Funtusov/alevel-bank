package ua.com.alevel.alevelbank.persistence.repository.token;

import org.springframework.stereotype.Repository;
import ua.com.alevel.alevelbank.persistence.entity.token.Token;
import ua.com.alevel.alevelbank.persistence.repository.AbstractRepository;

@Repository
public interface TokenRepository extends AbstractRepository<Token> {

    Token findByTokenUUID(String token);
}
