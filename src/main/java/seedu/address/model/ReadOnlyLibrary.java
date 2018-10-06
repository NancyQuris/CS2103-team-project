package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.playlist.Playlist;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyLibrary {

    /**
     * Returns an unmodifiable view of the playlists list.
     * This list will not contain any duplicate playlists.
     */
    ObservableList<Playlist> getPlaylistList();

}
