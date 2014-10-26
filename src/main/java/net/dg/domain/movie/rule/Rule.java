package net.dg.domain.movie.rule;

import net.dg.domain.movie.Showing;

public abstract class Rule {
    public boolean isStatisfiedBy(Showing showing) {
        return true;
    }
}
