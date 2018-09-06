package com.kernal.plateid.utills;


public interface I {
    String SERVER_ROOT = "http://mep2uf" +
            ".natappfree.cc/TestDemo04/";
    /** 下拉刷新*/

//    String SERVER_ROOT ="http://localhost:8080/TestDemo04/";
    int ACTION_DOWNLOAD=0;
    /** 第一次下载*/
    int ACTION_PULL_DOWN=1;
    /** 上拉刷新*/
    int ACTION_PULL_UP=2;

    /** 每行显示的数量columNum*/
    int COLUM_NUM = 2;


    /** 表示列表项布局的两种类型*/
    int TYPE_ITEM=0;
    int TYPE_FOOTER=1;

    int REQUEST_CODE_REGISTER = 101;
    int REQUEST_CODE_LOGIN = 102;
    int REQUEST_CODE_NICK = 103;
    int REQUEST_CODE_LOGIN_FROM_CART = 104;

    /** BeeColud APP ID */
    String BEE_COLUD_APP_ID = "3539b590-4859-4128-87a3-5fb8b86b94f6";
    /** BeeColud APP Secret*/
    String BEE_COLUD_APP_SECRET = "c75c74e1-105e-437c-9be9-84c4ddee4d5f";
    /** BeeColud APP Test Secret*/
    String BEE_COLUD_APP_SECRET_TEST = "06eb1210-0eeb-41df-99e3-1ffb9eb87b99";
    /** weixin APP ID */
    String WEIXIN_APP_ID = "wxf1aa465362b4c8f1";
    // 如果使用PayPal需要在支付之前设置client id和应用secret
    String PAYPAL_CLIENT_ID = "AVT1Ch18aTIlUJIeeCxvC7ZKQYHczGwiWm8jOwhrREc4a5FnbdwlqEB4evlHPXXUA67RAAZqZM0H8TCR";
    String PAYPAL_SECRET = "EL-fkjkEUyxrwZAmrfn46awFXlX-h2nRkyCVhhpeVdlSRuhPJKXx3ZvUTTJqPQuAeomXA8PZ2MkX24vF";

    //商户名称
    String MERCHANT_NAME = "福利社";

    //货币单位
    String CURRENCY_TYPE_CNY = "CNY";
    String CURRENCY_TYPE_USD = "USD";

    String TEXT_HTML = "text/html";

    String ACTION_TYPE_PERSONAL = "personal";
    String ACTION_TYPE_CART = "cart";

    /** 添加收藏*/
    int ACTION_ADD_COLLECT=1;
    /** 取消收藏*/
    int ACTION_DELETE_COLLECT=2;
    
    int NEW_GOOD=0;
    int CATEGORY_GOOD=1;
    int CAT_ID=0;
    String BROADCAST_UPDATA_CART = "cn.ucai.fulicenter.update.cart";
    String BROADCAST_DELETE_CART = "cn.ucai.fulicenter.delete.cart";
    int CART_CHECKED_DEFAULT = 0;
    String DETECT_TRUCKSTATUS = "TruckStatusDetect";
    String TRUCKSTATUS_UPDATE = "SetTruckStatus";

    String DATA_NAME_KEY = "name";
    String DATA_GENTDER_KEY = "gender";
    String DATA_CARD_KEY = "card";
    String DATA_DEPARTMENT_KEY="department";

    interface Goods {
        String KEY_RESULT = "result";
        String RESPONSE_SUCCESS = "success";
        String KEY_INFO = "info";
        String KEY_GOODS = "goods";
        String KEY_CURRENCY_PRICE = "currency_price";
        String KEY_GOODS_ID = "goods_id";
        String KEY_GOODS_NAME = "goods_name";
        String KEY_ENGLISH_NAME = "english_name";

        String HINT_DOWNLOAD_TITLE = "加载商品信息";
        String HINT_DOWNLOADING = "加载中...";
        String HINT_DOWNLOAD_FAILURE = "加载数据失败";
    }

    interface Boutique extends Goods {
        String ID="id";
        String CAT_ID="catId";
        String TITLE="title";
        String DESCRIPTION="description";
        String NAME="name";
        String IMAGE_URL="imageurl";
    }

    interface NewGoods extends Goods {
        String KEY_THUMB_URL = "thumb";
        String HINT_DOWNLOAD_TITLE = "加载新品列表";
        String HINT_DOWNLOADING = "加载中...";
        String HINT_DOWNLOAD_FAILURE = "加载数据失败";
    }

    interface GoodsDetails extends Goods {
        String HINT_DOWNLOAD_TITLE = "加载商品详细信息";
        String KEY_CAT_ID = "cat_id";
        String KEY_ENGLISH_NAME = "goods_english_name";
        String KEY_GOODS_BRIEF = "goods_brief";
        String KEY_GOODS_DESC = "goods_desc";
        String KEY_GOODS_IMG = "goods_img";
        String KEY_GOODS_THUMB = "goods_thumb";
        String KEY_SHOP_PRICE = "shop_price";
        String KEY_PROPERTIES = "properties";
        String KEY_ALBUMS = "albums";
    }

    interface Category extends Goods {
        String HINT_DOWNLOAD_TITLE = "加载分类列表";
        String KEY_ID = "id";
        String KEY_NAME = "name";
        String KEY_COLOR_ID = "colorid";
        String KEY_COLOR_NAME = "colorname";

        String KEY_CAT_ID = "cat_id";
        String KEY_CATEGORY_INFO = "category_info";
        String PARAM_PAGE = "&page=";
        String PARAM_C_ID = "&c_id=";
        String PARAM_CAT_ID = "&cat_id=";
        String PARAM_ORDER_PRICE = "&order_price=";
        String PARAM_COLOR_ID = "&getcolorid=";

        int SORT_DEFAULT = 0;// 排序默认值
        int SORT_PRICE_ASC = 1;// 价格升序排序
        int SORT_PRICE_DESC = 2;// 价格降序排序
        int SORT_DATE_ASC = 3;// 日期升序排序
        int SORT_DATE_DESC = 4;// 日期降序排序

        int COLOR_DEFAULT = -1;// 排序默认值
    }

    interface NewAndBoutiqueGoods{
        String CAT_ID="cat_id";
        /** 颜色id*/
        String COLOR_ID="color_id";
        /** 颜色名*/
        String COLOR_NAME="color_name";
        /** 颜色代码*/
        String COLOR_CODE="color_code";
        /** 导购链接*/
        String COLOR_URL="color_url";
    }

    interface CategoryGroup{
        String ID="id";
        String NAME="name";
        String IMAGE_URL="imageurl";
    }

    interface CategoryChild extends CategoryGroup{
        String PARENT_ID="parent_id";
        String CAT_ID="catId";
    }

    interface CategoryGood{
        String TABLE_NAME="tb_category_good";
        String ID="id";
        /** 商品id*/
        String GOODS_ID="goods_id";
        /** 所属类别的id*/
        String CAT_ID="cat_id";
        /** 商品的中文名称*/
        String GOODS_NAME="goods_name";
        /** 商品的英文名称*/
        String GOODS_ENGLISH_NAME="goods_english_name";
        /** 商品简介*/
        String GOODS_BRIEF="goods_brief";
        /** 商品原始价格*/
        String SHOP_PRICE="shop_price";
        /** 商品的RMB价格 */
        String CURRENT_PRICE="currency_price";
        /** 商品折扣价格 */
        String PROMOTE_PRICE="promote_price";
        /** 人民币折扣价格*/
        String RANK_PRICE="rank_price";
        /**是否折扣*/
        String IS_PROMOTE="is_promote";
        /** 商品缩略图地址*/
        String GOODS_THUMB="goods_thumb";
        /** 商品图片地址*/
        String GOODS_IMG="goods_img";
        /** 分享地址*/
        String ADD_TIME="add_time";
        /** 分享地址*/
        String SHARE_URL="share_url";
    }

    interface Property{
        String ID="id";
        String goodsId="goods_id";
        String COLOR_ID="colorid";
        String COLOR_NAME="colorname";
        String COLOR_CODE="colorcode";
        String COLOR_IMG="colorimg";
        String COLOR_URL="colorurl";
    }

    interface Album{
        String TABLE_NAME="tb_album";
        String ID="id";
        String PID="pid";
        String IMG_ID="img_id";
        String IMG_URL="img_url";
        String THUMB_URL="thumb_url";
        String IMG_DESC="img_desc";
    }

    interface Cart{
        String ID="id";
        String GOODS_ID="goods_id";
        String GOODS_THUMB="goodsThumb";
        String USER_NAME="userName";
        /**购物车中该商品的件数*/
        String COUNT="count";
        /**商品是否已被选中*/
        String IS_CHECKED="isChecked";
    }

    interface Collect{
        /** 商品id*/
        String ID="id";
        String GOODS_ID="goods_id";
        String USER_NAME="userName";
        /** 商品的中文名称*/
        String GOODS_NAME="goodsName";
        /** 商品的英文名称*/
        String GOODS_ENGLISH_NAME="goodsEnglishName";
        String GOODS_THUMB="goodsThumb";
        String GOODS_IMG="goodsImg";
        String ADD_TIME="addTime";
    }

    interface User {
        String TABLE_NAME = "t_superwechat_user";
        String USER_ID = "m_user_id";//主键
        String USER_NAME = "card_num";//用户账号
        String PASSWORD = "password";//用户密码
        String NICK = "m_user_nick";//用户昵称
        String UN_READ_MSG_COUNT = "m_user_unread_msg_count";//未读消息数量
    }

    interface Contact {
        String TABLE_NAME = "t_superwechat_contact";
        String CONTACT_ID = "m_contact_id";//主键
        String USER_ID = "m_contact_user_id";//用户id
        String USER_NAME = "m_contact_user_name";//用户账号
        String CU_ID = "m_contact_cid";//好友id
        String CU_NAME = "m_contact_cname";//好友账号
    }

    interface Avatar {
        String TABLE_NAME = "t_superwechat_avatar";
        String AVATAR_ID = "m_avatar_id";//主键
        String USER_ID = "m_avatar_user_id";//用户id或者群组id
        String USER_NAME = "m_avatar_user_name";//用户账号或者群组账号
        String AVATAR_PATH = "m_avatar_path";//保存路径
        String AVATAR_TYPE = "m_avatar_type";//头像类型：\n0:用户头像\n1:群组头像
    }

    /**
     * 商品排序方式
     */
    int SORT_BY_PRICE_ASC=1;
    int SORT_BY_PRICE_DESC=2;
    int SORT_BY_ADDTIME_ASC=3;
    int SORT_BY_ADDTIME_DESC=4;
    String ISON8859_1 = "iso8859-1";
    String UTF_8 = "utf-8";
    String PAGE_ID = "page_id";//分页的起始下标
    String PAGE_SIZE = "page_size";//分页的每页数量
    int PAGE_ID_DEFAULT = 1;//分页的起始下标默认值
    int PAGE_SIZE_DEFAULT = 10;//分页的每页数量默认值

    int ID_DEFAULT=0;//ID默认值
    int UN_READ_MSG_COUNT_DEFAULT=0;//未读消息数量默认值
    int GROUP_MAX_USERS_DEFAULT = -1;//群组最大人数默认值
    int GROUP_AFFILIATIONS_COUNT_DEFAULT = 1;//群组人数默认值
    int PERMISSION_NORMAL= 0;//普通用户群组权限
    int PERMISSION_OWNER= 1;//群组所有者群组权限
    int AVATAR_TYPE_USER=0;//用户头像
    int AVATAR_TYPE_GROUP=1;//群组头像
    int GROUP_PUBLIC=1;//公开群组
    int GROUP_NO_PUBLIC=0;//非公开群组
    String BACKSLASH= "/";//反斜杠
    String AVATAR_TYPE_USER_PATH= "user_avatar";//用户头像保存目录
    String AVATAR_TYPE_GROUP_PATH ="group_icon";//群组头像保存目录
    String AVATAR_SUFFIX_PNG=".png";//PNG图片后缀名
    String AVATAR_SUFFIX_JPG=".jpg";//JPG图片后缀名
    String QUESTION = "?";//问号
    String EQUAL = "="; //等号
    String AND = "&"; //&符号
    String MSG_PREFIX_MSG = "msg_"; //消息码前缀
    int LOCATION_IS_SEARCH_ALLOW=1;//可以被搜索到地理位置
    int LOCATION_IS_SEARCH_INHIBIT=0;//禁止被搜索到地理位置
    int MSG_CONNECTION_SUCCESS=  900;//连接服务器成功
    int MSG_CONNECTION_FAIL=  901;//连接服务器失败
    int MSG_UPLOAD_AVATAR_SUCCESS=902;//上传头像成功
    int MSG_UPLOAD_AVATAR_FAIL=903;//上传头像失败
    int MSG_REGISTER_SUCCESS=  101;//注册成功
    int MSG_REGISTER_USERNAME_EXISTS=102;//账号已经存在
    int MSG_REGISTER_UPLOAD_AVATAR_FAIL=103;//上传头像失败
    int MSG_REGISTER_UPLOAD_AVATAR_SUCCESS=104;//上传头像成功
    int MSG_REGISTER_FAIL=105;//注册失败
    int MSG_UNREGISTER_SUCCESS=  106;//注册成功
    int MSG_UNREGISTER_FAIL=107;//注册失败
    int MSG_CONTACT_FIRENDED=201;//已经是好友关系
    int MSG_CONTACT_FAIL=202;//好友关系
    int MSG_GROUP_CREATE_SCUUESS=301;//创建群组成功
    int MSG_GROUP_HXID_EXISTS=302;//群组环信ID已经存在
    int MSG_GROUP_CREATE_FAIL=303;//创建群组成功
    int MSG_GROUP_ADD_MEMBER_FAIL=304;//添加群组成员失败
    int MSG_GROUP_ADD_MEMBER_SCUUESS=305;//添加群组成员成功
    int MSG_GROUP_UNKONW=306;//群组不存在
    int MSG_GROUP_SAME_NAME=307;//群组名称未修改
    int MSG_GROUP_UPDATE_NAME_SUCCESS=308;//群组名称修改成功
    int MSG_GROUP_UPDATE_NAME_FAIL=309;//群组名称修改失败
    int MSG_GROUP_DELETE_MEMBER_SUCCESS=310;//删除群组成员成功
    int MSG_GROUP_DELETE_MEMBER_FAIL=311;//删除群组成员失败
    int MSG_GROUP_DELETE_SUCCESS=312;//删除群组成功
    int MSG_GROUP_DELETE_FAIL=313;//删除群组失败
    int MSG_LOGIN_UNKNOW_USER=401;//账户不存在
    int MSG_LOGIN_ERROR_PASSWORD=402;//账户密码错误
    int MSG_LOGIN_SUCCESS=403;//登陆成功
    int MSG_USER_SAME_NICK=404;//昵称未修改
    int MSG_USER_UPDATE_NICK_SUCCESS=405;//昵称修改成功
    int MSG_USER_UPDATE_NICK_FAIL=406;//昵称修改失败
    int MSG_USER_SAME_PASSWORD=407;//昵称未修改
    int MSG_USER_UPDATE_PASSWORD_SUCCESS=408;//昵称修改成功
    int MSG_USER_UPDATE_PASSWORD_FAIL=409;//昵称修改失败
    int MSG_LOCATION_UPLOAD_SUCCESS=501;//用户上传地理位置成功
    int MSG_LOCATION_UPLOAD_FAIL=502;//用户上传地理位置失败
    int MSG_LOCATION_UPDATE_SUCCESS=503;//用户更新地理位置成功
    int MSG_LOCATION_UPDATE_FAIL=504;//用户更新地理位置失败
    int MSG_UNKNOW=999;//未知错误
    int MSG_ILLEGAL_REQUEST=-1;    //非法请求

    /** 上传头像图片的类型：user_avatar或group_icon */
    String AVATAR_TYPE = "avatarType";
    String AVATAR_SUFFIX = "m_avatar_suffix";
    /** 用户的账号或群组的环信id */
    String NAME_OR_HXID = "name_or_hxid";
    /** 客户端发送的获取服务端状态的请求 */
    String REQUEST_SERVERSTATUS = "getServerStatus";
    /** 客户端发送的新用户注册的请求 */
    String REQUEST_REGISTER = "register";
    /** 下载图片通用的请求字段 */
    String IMAGE_URL="imageurl";
    /** 客户端发送的取消注册的请求 */
    String REQUEST_UNREGISTER = "unregister";
    /** 客户端发送的用户登录请求 */
    String REQUEST_LOGIN = "login";
    /** 客户端发送的下载用户头像请求 */
    String REQUEST_DOWNLOAD_AVATAR = "downloadAvatar";
    /** 客户端发送的上传/更新用户头像的请求 */
    String REQUEST_UPDATE_AVATAR = "updateAvatar";
    /** 客户端发送的更新用户昵称的请求 */
    String REQUEST_UPDATE_USER_NICK = "updateNick";
    /** 客户端发送的更新用户密码的请求 */
    String REQUEST_UPDATE_USER_PASSWORD = "updatePassword";
    /** 客户端发送的下载用户的好友列表的全部数据的请求 */
    String REQUEST_DOWNLOAD_CONTACT_ALL_LIST = "downloadContactAllList";
    /** 客户端发送的分页下载用户的好友列表的全部数据的请求 */
    String REQUEST_DOWNLOAD_CONTACT_PAGE_LIST = "downloadContactPageList";
    /** 客户端发送的添加好友的请求 */
    String REQUEST_ADD_CONTACT = "addContact";
    /** 客户端发送的删除好友的请求 */
    String REQUEST_DELETE_CONTACT = "deleteContact";
    /** 客户端发送的根据用户名查找用户信息的请求 */
    String REQUEST_FIND_USER = "findUserByUserName";
    /** 客户端发送的根据用户名或昵称模糊分页查找用户数据的请求 */
    String REQUEST_FIND_USERS_FOR_SEARCH= "findUsersForSearch";

    /** 从服务端查询精选首页的数据*/
    String REQUEST_FIND_BOUTIQUES="findBoutiques";
    /** 从服务端查询新品首页和精品二级页面的一组商品信息*/
    String REQUEST_FIND_NEW_BOUTIQUE_GOODS="findNewAndBoutiqueGoods";

    /** 从服务端下载分类首页大类数据*/
    String REQUEST_FIND_CATEGORY_GROUP="findCategoryGroup";

    /** 从服务端下载分类首页小类数据*/
    String REQUEST_FIND_CATEGORY_CHILDREN="findCategoryChildren";

    /** 从服务端下载指定商品详细信息的数据*/
    String REQUEST_FIND_GOOD_DETAILS="findGoodDetails";
    /** 添加收藏*/
    String REQUEST_ADD_COLLECT="addCollect";
    /** 删除收藏*/
    String REQUEST_DELETE_COLLECT="deleteCollect";
    /** 下载收藏的商品信息*/
    String REQUEST_FIND_COLLECTS="getUnTaggedTruckData";
    /** 下载收藏的商品数量信息*/
    String REQUEST_FIND_COLLECT_COUNT="findCollectCount";
    /** 添加商品至购物车*/
    String REQUEST_ADD_CART="addCart";
    /** 查询用户的购物车*/
    String REQUEST_FIND_CARTS="findCarts";
    /** 删除购物车中的商品*/
    String REQUEST_DELETE_CART="deleteCart";
    /** 修改购物车中的商品的信息*/
    String REQUEST_UPDATE_CART="updateCart";
    /**下载新品首页商品图片*/
    String REQUEST_DOWNLOAD_NEW_GOOD = "downloadNewGood";
    /** 下载商品相册图像的请求*/
    String REQUEST_DOWNLOAD_ALBUM_IMG="downloadAlbumImg";
    /** 查询是否已收藏*/
    String REQUEST_IS_COLLECT="isCollect";
    /** 下载精选首页图像的请求*/
    String REQUEST_DOWNLOAD_BOUTIQUE_IMG="downloadBoutiqueImg";
    /** 下载分类商品大类图像的请求*/
    String REQUEST_DOWNLOAD_CATEGORY_GROUP_IMAGE="downloadCategoryGroupImage";
    /** 下载分类商品小类图像的请求*/
    String REQUEST_DOWNLOAD_CATEGORY_CHILD_IMAGE="downloadCategoryChildImage";

    /** 从服务端下载分类二级页面一组商品详情的数据*/
    String REQUEST_FIND_GOODS_DETAILS="findGoodsDetails";
    /** 下载商品图像的请求*/
    String REQUEST_DOWNLOAD_GOODS_THUMB="downloadGoodsThumb";
    /** 查询支付情况请求*/
    String REQUEST_FIND_CHARGE = "findCharge";
    /** 支付请求*/
    String REQUEST_PAY="pay";
    /** 下载图片的请求*/
    String REQUEST_DOWNLOAD_IMAGE="downloadImage";

    /** 上传图片的请求*/
    String REQUEST_UPLOAD_IMAGE="uploadpic";

    /** 下载精选首页图像的接口*/
    String DOWNLOAD_IMG_URL= I.SERVER_ROOT+
            REQUEST_DOWNLOAD_IMAGE+ I.QUESTION+ IMAGE_URL+"=";

    String DOWNLOAD_AVATAR_URL = I.SERVER_ROOT +
            REQUEST_DOWNLOAD_AVATAR + I.QUESTION;
}
