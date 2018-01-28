package com.bwei.ssp.home_work.Fragment.Shouye.Search.greendao;

import com.bwei.ssp.home_work.Fragment.Shouye.Search.database.DaoSession;
import com.bwei.ssp.home_work.Fragment.Shouye.Search.database.SearchContentDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by lenovo on 2017/12/29.
 */
@Entity(active = true)
public class SearchContent {
    @Id(autoincrement = true)
    Long id;
    String but_text;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1320034884)
    private transient SearchContentDao myDao;

    @Generated(hash = 867702201)
    public SearchContent(Long id, String but_text) {
        this.id = id;
        this.but_text = but_text;
    }

    @Generated(hash = 206336392)
    public SearchContent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBut_text() {
        return but_text;
    }

    public void setBut_text(String but_text) {
        this.but_text = but_text;
    }

    @Override
    public String toString() {
        return "SearchContent{" +
                "id=" + id +
                ", but_text='" + but_text + '\'' +
                '}';
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 975639860)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSearchContentDao() : null;
    }
}
