package seedu.jxmusic.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.jxmusic.commons.core.Messages;
import seedu.jxmusic.model.Model;
import seedu.jxmusic.model.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in jxmusic book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPlaylistList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PLAYLISTS_LISTED_OVERVIEW, model.getFilteredPlaylistList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}