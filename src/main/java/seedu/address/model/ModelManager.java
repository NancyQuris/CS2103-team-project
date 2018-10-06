package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.LibraryChangedEvent;
import seedu.address.model.playlist.Playlist;

/**
 * Represents the in-memory model of the library data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Library library;
    private final FilteredList<Playlist> filteredPlaylists;

    /**
     * Initializes a ModelManager with the given library and userPrefs.
     */
    public ModelManager(ReadOnlyLibrary readOnlyLibrary, UserPrefs userPrefs) {
        super();
        requireAllNonNull(readOnlyLibrary, userPrefs);

        logger.fine("Initializing with library: " + readOnlyLibrary + " and user prefs " + userPrefs);

        library = new Library(readOnlyLibrary);
        filteredPlaylists = new FilteredList<>(library.getPlaylistList());
    }

    public ModelManager() {
        this(new Library(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyLibrary newData) {
        library.resetData(newData);
        indicateModelChanged();
    }

    @Override
    public ReadOnlyLibrary getLibrary() {
        return library;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateModelChanged() {
        raise(new LibraryChangedEvent(library));
    }

    @Override
    public boolean hasPlaylist(Playlist playlist) {
        requireNonNull(playlist);
        return library.hasPlaylist(playlist);
    }

    @Override
    public void deletePlaylist(Playlist target) {
        library.removePlaylist(target);
        indicateModelChanged();
    }

    @Override
    public void addPlaylist(Playlist playlist) {
        library.addPlaylist(playlist);
        updateFilteredPlaylistList(PREDICATE_SHOW_ALL_PLAYLISTS);
        indicateModelChanged();
    }

    @Override
    public void updatePlaylist(Playlist target, Playlist editedPlaylist) {
        requireAllNonNull(target, editedPlaylist);

        library.updatePlaylist(target, editedPlaylist);
        indicateModelChanged();
    }

    //=========== Filtered Playlist List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Playlist} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Playlist> getFilteredPlaylistList() {
        return FXCollections.unmodifiableObservableList(filteredPlaylists);
    }

    @Override
    public void updateFilteredPlaylistList(Predicate<Playlist> predicate) {
        requireNonNull(predicate);
        filteredPlaylists.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return library.equals(other.library)
                && filteredPlaylists.equals(other.filteredPlaylists);
    }

}
