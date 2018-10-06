package seedu.address.model.playlist;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.model.Track;

/**
 * Represents a Playlist in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Playlist {

    // Identity fields
    private final Name name;

    // Data fields
    private final List<Track> tracks = new ArrayList<>();

    /**
     * Only name field must be present. List of tracks to be added later.
     */
    public Playlist(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns the list of tracks in the playlist
     */
    public List<Track> getTracks() {
        return Collections.unmodifiableList(tracks);
    }

    /**
     * Adds a track into the playlist
     * @param track to be added to the playlist, must not be null
     */
    public void addTrack(Track track) {
        if (track == null) {
            throw new NullPointerException("track must not be null");
        }
        tracks.add(track);
    }

    /**
     * Returns true if both playlists have the same name
     * This defines a weaker notion of equality between two playlists.
     */
    public boolean isSamePlaylist(Playlist otherPlaylist) {
        if (otherPlaylist == this) {
            return true;
        }

        return otherPlaylist != null
                && otherPlaylist.getName().equals(getName());
    }

    /**
     * Returns true if both playlists have the same name and tracks.
     * This defines a stronger notion of equality between two playlists.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Playlist)) {
            return false;
        }

        Playlist otherPlaylist = (Playlist) other;
        return otherPlaylist.getName().equals(getName())
                && otherPlaylist.getTracks().equals(getTracks());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tracks);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Tracks: ");
        tracks.forEach(builder::append);
        return builder.toString();
    }

}
