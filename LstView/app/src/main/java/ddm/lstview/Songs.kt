package ddm.lstview

/**
 * Created by mac on 29/09/2017.
 */
class Songs {

    var mSongURL: String? = null
    var mSongTitle: String? = null
    var mSongArtist: String? = null

    constructor(mSongURL: String, mSongTitle: String, mSongArtist: String) {
        this.mSongURL = mSongURL
        this.mSongTitle = mSongTitle
        this.mSongArtist = mSongArtist
    }
}