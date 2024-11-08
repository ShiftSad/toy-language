package org.example.toylanguage;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Chunk;
import org.example.toylanguage.context.ExceptionContext;
import org.example.toylanguage.context.MemoryContext;
import org.example.toylanguage.context.definition.DefinitionContext;
import org.example.toylanguage.minecraft.PlayerHierarchy;
import org.example.toylanguage.statement.CompositeStatement;
import org.example.toylanguage.token.Token;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
public class ToyLanguage {

    // The collection of chunks the sandbox can interact with.
    private final Collection<Chunk> chunks;
    // The map of players the sandbox can access, and it's hierarchy
    private final HashMap<Player, PlayerHierarchy> players;

    @SneakyThrows
    public void execute(Path path) {
        String sourceCode = Files.readString(path);
        List<Token> tokens = LexicalParser.parse(sourceCode);

        DefinitionContext.pushScope(DefinitionContext.newScope());
        MemoryContext.pushScope(MemoryContext.newScope());
        try {
            CompositeStatement statement = new CompositeStatement(null, path.getFileName().toString());
            StatementParser.parse(tokens, statement);
            statement.execute();
        } finally {
            DefinitionContext.endScope();
            MemoryContext.endScope();

            if (ExceptionContext.isRaised()) {
                ExceptionContext.printStackTrace();
            }
        }
    }

}
