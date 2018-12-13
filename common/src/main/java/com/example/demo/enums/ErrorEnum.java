package com.example.demo.enums;

public enum ErrorEnum {
    /**
     * 接口调用成功
     */
    SUCCESS(0, "接口调用成功"),

    /**
     * OSS相关
     */
    ERROR_OSS_FILE_NOT_EXISTS(0001,"文件不存在"),
    ERROR_OSS_FILE_EXISTS(0002,"文件已存在"),
    ERROR_OSS_FILE_MOVE_CRITIRA(0003,"源文件不存在或目标文件已存在"),
    ERROR_OSS_FILE_GETSTREAM(0010,"获取文件流异常"),
    ERROR_OSS_PARAM_WRONG(0011,"参数错误"),
    ERROR_OSS(0012,"上传文件失败"),
    ERROR_OSS_FILE_OUT_MAX_SIZE(0013, "文件过大,请修改后重新上传"),
    /**
     * 系统默认error
     */
    SYSTEM_UNKONW_ERROR(0001, "未知异常"),
    SERVER_ERROR(9901, "服务器通讯失败"),
    /**
     * 权限相关（10**）
     */
    ACCESS_IS_NULL(1001, "该用户角色未被赋予权限"),
    NO_VAILED_ACCESS(1002, "用户权限不满足"),
    LOSE_REQUEST_URI(1003, "缺少请求URI"),
    LOST_PERMISSION_RANGE(1004, "缺少权限范围"),
    NONE_SYSTEM_PERMISSION_TREE(1005, "系统权限树暂未配置"),
    SYSPERMISSION_PARAM_IS_ERROR(1006, "传入系统权限参数有误！"),
    ERROR_FATHER_WEB_PAGE(1007, "错误的父页面"),
    DUMPLICATE_SYS_PER_CODE(1008, "重复的权限Code"),
    DUMPLICATE_SYS_PER_GROUP_CODE(1009, "重复的权限组Code"),
    DUMPLICATE_COMPONENT(1010,"重复的权限Component"),
    /**
     * 用户相关（11**）
     */
    ACCOUNT_OR_PASSWORD_NULL(1100,"用户名或密码为空！"),
    USER_NOT_LOGIN(1101,"用户未登录"),
    USER_FROZEN(1102, "账户已被冻结2小时,冻结时间：{0} ,您可以联系管理员解除冻结状态"),
    USER_IS_NOT_EXIT(1003,"用户不存在"),
    PASSWORD_IS_WRONG(1004,"账户名或密码错误"),
    NOT_ALLOW_LOGIN(1005,"没有登录权限"),
    USER_HAS_DIMISSION(1106,"用户已经注销，无法登录！"),
    SSO_ERROR(1107,"调用sso登录失败"),

    USER_WORK_ID_EXIST(1109,"同一员工号只能使用一次:{0}"),
    USER_IDENTITY_EXIST(1110,"同一身份证只能使用一次:{0}"),
    USER_MAIL_EXIST(1111,"同一公司邮箱只能使用一次:{0}"),
    USER_NOT_EXIST(1112,"用户不存在"),

    LOGOUT_FAILED(1115, "注销失败"),
    FIND_ASSIGNEE_ERROR(1118, "找不到所需用户信息"),
    LEADER_CONTAINS_MYSELF(1119,"所选直接领导的上级中包含本人"),
    ONLY_COMMON_DEPT_HAVE_USER(1120,"人员的直属部门只能是普通部门,不能是总部或者分部"),
    TWO_PASSWORD_IS_NOT_SAME(1121,"两次密码不一致"),
    OLD_PASSWORD_IS_ERROR(1122,"旧密码输入错误"),
    DUPLICATION_ROLE_NAME(1123,"重复的角色名称！"),
    ALREADY_IN_USER_ROLE(1124,"角色已被使用，不能删除！"),
    OVER_DUE_WF_INFO(1125,"当前流程状态已更新，请返回首页重新查看该流程工单"),


    /**
     * 部门相关（12**）
     */
    DEPT_PARAM_ERROR(1200,"部门参数有误"),
    DEPT_PARAM_NULL(1201,"部门参数缺失"),
    DEPT_NAME_EXIST(1202,"部门名已存在"),
    DEPT_NOT_EXIST(1203,"部门缺失"),
    DEPT_MISS_BRANCH(1204,"缺失上级分部"),
    DEPT_USER_EXIST(1205,"部门下存在未离职的用户"),
    DEPT_EXIST(1206,"部门下存在未封存的部门"),
    SUP_DEPT_NOT_SELF(12307,"上级部门/分部不能修改为本部门/分部"),
    SUP_DEPT_NOT_BELOW_DEPT(12308,"上级部门/分部不能修改为本部门/分部的下级"),

    /**
     * 流程相关（13**）
     */
    WF_PARAM_ERROR(1300,"流程参数有误"),
    WF_PARAM_NULL(1301,"流程参数缺失"),
    LEAVE_TIME_PARAM_ERROR(1302, "请假流程开始或结束时间有误！"),
    LEAVE_TIME_COMPARE_ERROR(1303, "请假流程结束时间应大于起始时间！"),
    LEAVE_DAYS_PARAM_ERROR(1304, "请假天数应为正数且为0.5的倍数！"),
    UNREASONABLE_LEAVE_APPLICATION(1305, "当前请假天数超出额度！"),
    WF_UNCONTAIN_USER(1306,"所选人员超出权限范围"),
    WF_UNCONTAIN_DEPT(1307,"所选部门超出权限范围"),
    WF_UNCONTAIN_BRANCH(1308,"所选分部超出权限范围"),
    WF_UNCONTAIN_CONDITION(1309,"所选条件超出权限范围"),
    WF_TASK_IS_NULL(1310,"未找到对应的任务"),
    LEAVE_DAYS_UNCHECKED(1311,"请假天数应为0.5的倍数！"),
    LEAVE_DAYS_RELATION_ERROR(1312, "当前填写的请假天数与点选的请假起止时间不符合！"),
    STARTTIME_NEED_LESS_THEN_ENDTIME(1313,"起始时间应小于结束时间"),
    TIME_LESS_OR_EQUAL_DAYS(1314,"起始结束时间差需要小于等于天数"),
    DAYS_NOT_LESS_THAN_TIME(1314,"天数应大于等于起始结束时间差"),
    DAYS_FORMART(1315,"天数应为0.5的正倍数"),
    LOSE_LEAVE_TYPE(1316, "请假类型缺失！"),
    CAN_NOT_OPR_WF_FLOW(1317, "当前选择流程不可使用"),
    INVOICE_IS_REQUIRED(1318,"缺少发票!"),
    REIMBURSE_DETAIL_IS_NULL(1319,"报销明细为空"),

    /**
     * 文件相关
     */
    EXCEL_POST_ERROR(1403,"没有该岗位ID:{0}"),
    EXCEL_DEPT_ERROR(1404,"没有该部门ID:{0}"),
    EXCEL_IDENTITY_ERROR(1405,"该身份证格式错误:{0}"),



















    EXCEL_HEADER_HAS_NULL(9996,"Excel表头有空的列"),
    EXCEL_FALSE(9997,"模板错误"),
    EXCEL_NULL(9998,"EXCEL文件是空文件"),
    SOME_EXCEL_FALSE(9999,"读取excel失败，请仔细检查您的输入，是否有填入excel函数的情况"),

    /**
     * 接口调用成功
     */
    CALLCENTER_SUCCESS(1000, "外呼接口调用成功"),
    /**
     * 系统默认error
     */
    SQL_INSERT_NO_INFECT_ERROR(1002, "数据库插入影响0行"),

    /**
     * 用户相关 90**
     */
    //9002-用户名或者密码错误
    CONFIRM_PASSWORD_WRONG(9002, "用户名或者密码错误，您还可以尝试{0}次"),
    // 9003- 用户不存在
    USER_NOT_EXITS(9003, "用户不存在或已删除"),
    // 9004-密码为空
    USERNAME_PASSWORD_NULL(9004, "密码不能为空"),
    // 9005-未知错误
    UNKNOWN_WRROR(9005, "未知错误"),
    //9006-密码错误
    PASSWORD_WRONG(9006, "密码错误"),
    // 9007-新密码与旧密码不能相同
    SAME_PASSWORD(9007, "新密码与旧密码不能相同"),
    //9008-新密码与确认密码不一致
    DOUBLE_PASSWORD_ERROR(9008, "新密码与确认密码不一致"),
    //9009-无法获取登录信息，请先登录
    NOT_LOGIN(9009, "无法获取登录信息，请先登录"),
    NO_ACCESS(9010, "用户权限不满足"),
    NO_ACESS_RESTPWD(9011, "抱歉，您没有权限重置密码，请联系管理员~"),
    PASSWORD_TOO_LOW(9012, "密码长度小于8，请重新设置"),
    PASSWORD_WORD_NUM(9013, "密码必须包含至少一个大写字母、小写字母、数字"),
    PASSWORD_SIMPLE_NUM(9014, "密码过于简单，请重新设置"),
    PASSWORD_TOO_LONG(9015, "密码长度超过最大值，请重新设置"),
    WORKID_IS_NULL(9016, "工号为空"),
    UPDATE_USER_INFO_FAIL(9017, "更新用户信息失败"),
    CREATE_USER_GROUP_FAIL(9018, "当前小组已经存在组长"),
    CREATE_USER_SUPERVISOR_FAIL(9019, "当前模块已经存在主管"),
    USER_FREEZED(9020, "账户已被冻结2小时,冻结时间：{0} ,您可以联系管理员解除冻结状态"),
    USER_FREEZING(9021, "密码错误次数过多，账户冻结2小时"),
    USER_FROZEN_AND_FREEZING(9022, "用户冻结解冻失败"),
    USER_DELETED(9023, "用户被冻结，请联系管理员"),
    USER_ID_IS_NULL(9024, "用户号为空"),
    SESSION_TIME_OUT(9025, "logout session超时"),
    USER_BYPASS_AND_UNBYPASS(9026,"用户分件操作失败"),
    EXPORT_ERROR(9027, "导出报错啦！"),
    COMPANY_FREEZE(9028,"您所在的公司被冻结，请联系超级管理员"),
    SUPER_PERMISSION(9029,"用户不是超级管理员不能公司权限查询以及权限的修改"),
    USER_DEP_IS_NULL(9030,"用户部门为空"),
    USER_WRAPPER_PARAM_MISS(9031, "登录用户部分参数丢失"),
    NO_USER(9032,"部门或小组下没有用户"),
    USER_MODIFY_ERROR(9033,"更新用户信息失败"),
    COMPANY_SELECT_PAGE_ERROR(9034,"设置公司权限异常：选择的页面有误！"),
    COMPANY_SELECT_PERMISSION_ERROR(9035,"设置公司权限异常：选择的权限有误！"),
    COMPANY_SELECT_RANGE_ERROR(9036,"设置公司权限异常：选择的权限范围有误！"),
    CAN_NOT_SWITCH_POSITION(9037,"运维角色和运营角色不能相互转换"),
    DELETE_USER_NOT_EXIST(9038,"要删除的用户不存在"),
    DELETE_USER_ERROR(9040,"删除用户失败"),
    HAS_USER_IN_GROUP(9041, "当前小组已经存在组长"),
    DO_NOT_HAVE_CHANNEL_ID(9042, "创建甲方人员有且只有一个渠道ID"),
    ILLEGAL_ROLE_ID(9043,"选择的部门不包含该用户职位，请重新选择！"),
    LOSE_COMPANY_ROLE(9044, "公司角色配置有误，请联系管理员"),
    ROLE_RANGE_WRONG(9045,"角色权限不符"),
    GROUP_DATA_NOT_EXISTS(9046,"无小组排名数据"),
    REDEPLOY_OR_NOT_ERROR(9047,"设置用户可转派或禁止转派出错"),
    BYPASS_OR_NOT_ERROR(9048,"设置用户可分件或禁止分件出错"),
    CURRENT_USER_HASWO(9049,"当前角色有工单未完成处理"),
    QUALITY_CONTROL(9050,"设置用户可质检或禁止质检出错"),
    QUALITY_TIME_ERROR(9051,"设置用户可质检时间段出错"),
    SET_QUALITY_TIME_ERROR(9052,"结束时间需大于开始时间"),
    USER_QUERY_DEPT_ERROR(9053,"您没有权限查询该部门人员"),
    USER_ONLINE_NUM_ERROR(9054,"最大登录数应不小于0，不大于10"),
    NO_VAILED_USER(9056,"没有正确的可创建的用户"),
    NO_VAILED_DEL_USER(9057,"无可删除的用户"),
    CANNOT_DEL_OWN_OR_SUPER(9058,"不能删除自己或超级管理员"),
    NO_USER_NAME(9059,"用户名为空"),
    RESET_PWD_ERROR(9060,"重置密码失败"),
    UPDATE_PWD_ERROR(9061,"修改密码失败"),
    GLOBAL_ID_IS_EXIT(9062,"该全局账户名已被其他用户绑定,请更换账户！"),
    TOKEN_IS_INVALID(9063, "token无效"),
    TOKEN_GENERATE_ERROR(9065,"Token生成异常"),
    ERROR_DATA(9066,"异常数据"),
    NO_PERMISSION_CHECK_ACCOUNT(9067,"当前系统没有权限"),
    CALLING_SSO_ERROR(9068,"调用SSO接口异常"),

    /**
     * 催收记录相关异常 50**
     */
    MISS_TIME_AND_MONEY(5001, "缺少承诺还款时间或者缺少承诺还款金额"),
    NO_NEED_TIME_OR_MONEY(5002, "无需填写还款时间和还款金额"),
    PARAM_IS_NULL(5003, "参数不能为空"),
    PARAM_IS_ERROR(5004, "传入的参数有误"),
    REIMBERSEMENT_TIME_ERROR(5005, "还款时间不能小于当天"),
    INVALID_RESULT_TYPE(5006, "请选择有效的的催收结果类型"),
    NO_CALL(5007, "请先拨号后再提交备注"),
    NO_SAME_PHONE(5007, "与上次拨号号码不一致，请核对信息"),
    REMARK_EMPTY_ERROR(5008, "备注不能空"),
    MAX_INPUTCOUNT_ERROR(5009, "最大输入长度不能超过400个字~"),
    INPUT_MONEY_ERROR(5010, "只能输入金额大于0的数字~"),
    SCORE_VALIDATE_FAIL(5011, "评分分数校验未通过"),
    HAS_MAKE_SCORE(5012, "不能重复打分"),
    ROLE_TYPE_IS_NULL(5013, "角色类型为空"),
    NOT_ALLOW_CHANGE_TO_PARTY_A(5014, "不允许变更为甲方"),
    NOT_ALLOW_CHANGE_TO_OPERATION(5015,"不允许变更为运维"),
    NOT_ALLOW_CHANGE_TO_OPERATION_MANAGER(5015,"不允许变更为运维经理"),
    MONEY_ERROR(5016, "请输入正确的金额"),
    MAX_REDUCE_INPUTCOUNT_ERROR(5017, "最大输入长度不能超过300个字~"),
    CSRECORD_NULL(5028,"没有选择案件"),
    CSRECORD_NOT_HAVE(5029,"案件没有催记记录"),
    NO_VALID_CSRECORD(5030,"不符合催记格式"),
    DATA_INSERT_EX(5031,"入库时出现异常"),
    CASE_IS_NULL(5032,"没有正确的案件"),
    NO_VALID_INFO_REPAIR(5033,"不符合信修上传格式"),
    CS_RECORD_IS_NULL(5034,"没有正确的催记信息可导入"),
    INVAILED_ROLE_VIEW_OPERATION(5035,"查看催记导入记录的角色不对"),
    CS_RECORD_ADD_ILLEGAL(5036,"催记录入非法"),
    CONTACT_LIST_TOO_LONG(5037,"联系号码数量过多"),
    ADD_RECORD_NOT_MINE(5038,"抱歉，您没有权限添加不是自己案件的催记"),
    ADD_RECORD_CASE_STATUS_ERROR(5039,"抱歉，已完成或已撤回案件无法添加催记"),

    //文件上传相关异常
    FILE_IS_EMPTY(5015, "文件是空的"),
    ILLEGAL_FILE_FORMAT(5016, "文件格式不正确"),
    UPLOAD_FILE_FAIL(5017, "上传文件失败"),
    PAGE_SIZE_ERROR(5018, "分页请求参数非法"),
    NOT_FIND_CASE(5019, "案件编号不存在"),
    NO_HANDLER(5020, "提交人没有权限处理该案件"),
    COMPILE_FILE_FAIL(5021, "解析文件失败"),
    FILE_EXIST(5022, "文件重名"),
    FILE_NAME_TOO_LONG(5023, "文件名过长"),
    FILE_OUT_MAX_SIZE(5024, "文件过大,请上传100M以内的附件"),
    DISSCUSSACOUNT_TYPE_NULL(5025, "风险类型不能为空"),
    STICK_ERROR(5026, "抱歉，置顶失败"),
    CANCEL_STICK_ERROR(5027, "抱歉，取消置顶失败"),
    CS_TIME_ERROR(5015, "还款时间需要精确到分"),
    FAIL_UNZIP_FILE(5016, "解压出错"),
    //cos返回错误码(9开头5位)
    NOT_FIND_ERROR(99999, "调用远程接口失败"),
    COS_SERVICE_ERRPR(99998, "调用腾讯云COS接口失败"),
    BUCKETNAME_NULL(91000, "Bucket名称不能为空"),
    COSPATH_NULL(91001, "cos路径不能为空"),
    COSPATH_INVALID_FORMATE(91002, "cos路径格式不正确"),
    LOCALPATH_NOT_EXISTS(91003, "待上传文件不存在"),
    EXT_NOT_EQUAL(91004, "本地保存文件扩展名与服务器文件不一致"),
    FILE_EXT_ERROR(91005, "文件扩展名错误"),
    LOCALPATH_INVALID_FORMATE(91006, "本地文件路径格式不正确"),
    LOCALPATH_NULL(91007, "本地文件路径不能为空"),
    READ_LOCALFILE_ERROR(91008, "读取文件失败"),
    READ_STREAM_ERROR(91009, "读取输入流数据失败"),
    SAVE_FILE_ERROR(91010, "保存临时文件失败"),
    LOST_RECOVERY_DATE(91011, "缺失委案回收日"),
    ILLEGAL_RECOVERY_DATE(91012, "委案回收日必须大于当前时间"),
    TOO_BIG_UPLOAD_FILE(91013, "上传转信修格式文件不应超过3M"),
    //COS官方错误码
    ERROR_CMD_FILE_NOTEXIST(92000, "查无此文件"),
    ERROR_CMD_FILE_MOVE_ERROR(92001, "文件移动失败"),
    ERROR_CMD_BACKEND_NETWORK(92002, "网络请求失败"),
    ERROR_CMD_COS_FILE_EXIST(92003, "文件已存在"),
    ERROR_OSS_FILESIZE(92004, "文件过大"),
    ERROR_OSS_MKDIR(92005, "创建目录失败"),
    ERROR_OSS_WRITE_FILE(92006, "写文件失败"),
    ERROR_OSS_FILE_NOTEXIST(92007, "文件不存在"),
    ERROR_OSS_READ_FILE(92008, "读文件失败"),

    RATE_NULL(92009,"第{0}行第{1}列的费率必填"),
    PHASE_NULL(92010,"第{0}行第{1}列的案件阶段必填"),
    LENDING_INSTITUTION_NULL(92011,"第{0}行第{1}列的委案机构必填"),
    PHONE_NULL(92012,"第{0}行第{1}列的手机号必填"),
    ID_NUMBER_NULL(92013,"第{0}行第{1}列的身份证号必填"),
    CLEAR_PRICE_NULL(92014,"第{0}行第{1}列的结清金额必填"),
    CASE_BATCH_NULL(92015,"第{0}行第{1}列的案件批次必填"),
    PHASE_ERROR(92016,"第{0}行第{1}列的案件阶段格式有误"),
    RATE_ERROR(92017,"第{0}行第{1}列的费率格式有误"),
    AMOUNT_ERROR(92018,"第{0}行第{1}列的金额格式有误"),
    DATE_FORMAT_ERROR(92019,"第{0}行第{1}列的时间格式有误"),
    GENDER_ERROR(92020,"第{0}行第{1}列的性别输入有误"),
    DAYS_ERROR(92021,"第{0}行第{1}列的天数输入有误"),
    RECYCLE_DATE_NULL(92022,"第{0}行第{1}列的委案回收日必填"),
    TIME_BIGGER_THAN_TODAY(92023,"第{0}行第{1}列的委案回收日需大于当前操作日期"),
    CASE_HANDLER_NULL(92024,"第{0}行第{1}列的案件跟进人必填"),
    DURATION_ERROR(92025,"第{0}行第{1}列的时长有误"),
    DATE_FORMAT_ERROR_WITH_SHEETNO(92026,"{0}的第{1}行第{2}列的时间格式有误"),
    PUT_THROUGH_RATE_ERROR(92027,"{0}的第{1}行第{2}列的呼出接通率格式有误"),
    PATTERN_ERROR(92028,"第{0}行第{1}列的格式有误"),
    PHONE_ERROR(92029,"第{0}行第{1}列的手机号格式有误"),
    PHONE_ERROR_WITH_SHEETNO(92030,"{0}第{1}行第{2}列的手机号格式有误"),

    /**
     * 案件相关 80**
     */
    CASE_NOT(8003, "请输入正确的查询条件"),
    CASE_NOT_EXITS(8004, "贷款信息不存在，请联系管理员"),
    CASE_NOT_MATCH(8005, "转派的信息不匹配"),
    CASE_DEP_USER_EMPTY(8006, "转派的小组不存在或小组成员为空"),
    CASE_TOO_MUCH(8007, "手动导入的案件超过限制"),
    CASE_USER_MATCH(8010, "案件转派时有不合规定的用户"),
    CASE_MATCH(8008, "案件转派时有不合规定的案件"),
    CASE_TURN_NOT(8009, "案件为空"),
    CASE_PERMISSION_NOT(8010, "当前权限某些案件不能转派"),
    CASE_IDENTITY_NOT_EXITS(8011, "贷款人不存在，请联系管理员"),
    CASE_QUERY_CONDITION_ERROR(8012, "案件查询条件不符合要求"),
    CASE_QUERY_NOT(8888, "案件批量查询时,存在不合适的入参"),
    CASE_REPORTSELF_PERMISSION_ERROR(8889, "抱歉，您没有权限提报自己的商账"),
    CASE_REPORTOTHER_PERMISSION_ERROR(8890, "抱歉，您没有权限提报他人的商账"),
    CASE_XIAMA_SELF_PERMISSION_ERROR(8891, "抱歉，您没有权限下自己的码"),
    CASE_XIAMA_OTHER_PERMISSION_ERROR(8892, "抱歉，您没有权限下别人的码"),
    CASE_BAMA_SELF_PERMISSION_ERROR(8893, "抱歉，您没有权限拔自己的码"),
    CASE_BAMA_OTHER_PERMISSION_ERROR(8894, "抱歉，您没有权限拔别人的码"),
    CASE_ATTACHMENT_ERROR(8890, "请上传还款凭证"),
    CASE_WAIT_RETURN_MONEY_ERROR(8891, "已完成的案件不能还款待确认"),
    CASE_NEICUI_ERROR(8892, "內催案件不能还款待确认"),
    CASE_REDUCE_MONEY_ERROR(8893, "抱歉,该案件您没有权限申请罚息减免"),
    CASE_REDUCE_ERROR(8894, "申请的罚息减免金额不符合"),
    CASE_REDUCE_SAME_ERROR(8895,"当前申请的罚息减免已有记录"),
    CASE_HAVE_DONE_ERROR(8896,"当前申请的罚息减免案件为已完成状态"),
    CASE_QUERY_HAVE_WRONG(8897,"您输入的案件，有{}条无法查询到"),
    CASE_IS_NOW_UPDATING(8898,"该案件已经在更新中，请勿重复更新，谢谢！"),
    CASE_TURN_ERROR(8899,"案件转派异常"),
    CASE_EXPORT_NULL(8900,"案件导出为空"),
    CASE_NOT_SPECIAL(8901,"非志任公司没有特殊案件"),
    CASE_TURN_DATE_ERROR(8813,"案件转派时间异常"),
    CASE_TURN_MODULE_OR_GROUPS_ERROR(8814,"主管或小组只能转派自己模块下的案件"),
    CASE_AUTHORITY_ERROR(8815,"操作超出权限范围"),
    CASE_LENDING_INSTITUTION_NOT_EXITS(8816,"案件渠道不存在，请联系管理员"),
    NO_AUTHORITY_DEPARTMENT(8817,"您没有权限选择该部门！"),
    INTEGER_PARAM_ERROR(8818,"参数有误"),
    CASE_OVERDUE_DAY_VALUE_ERROR(8819,"最小逾期天数应小于最大逾期天数"),
    CONTACT_LIST_ERROR(8820,"通话详单展示有误，脱敏且无手机号"),
    TEMP_NAME_EXIST(8821,"模板名称已存在"),
    SEARCH_TEMP_NOT_EXIST(8822,"查询模板不存在"),
    ASSIGN_TEMP_NOT_EXIST(8823,"分配模板不存在"),
    CANT_DELETE_TEMP(8824,"该模板正在被转派策略:{0} 使用，请先修改策略后再删除"),
    PARAM_WRONG(8825,"参数有误"),
    PARAM_PROPORTION(8826,"请仔细填写比例"),
    GROUP_USER_IS_NULL(8827,"包含成员为空的部门"),
    HANDLER_DEPT_ALL_NULL(8828,"请选择部门或组员"),
    BLACK_AND_WHITE_LIST_IS_NULL(8829,"没有可以录入的黑白名单"),

    /**
     * 委外相关 89**
     */
    OUTSOURCING_PARAM_STATUS_ERROR(8900,"参数错误：委外的案件状态不符合要求！"),
    OUTSOURCING_PARAM_OUTSOURCING_ERROR(8901,"参数错误：已委外的案件不能再次委外！"),
    OUTSOURCING_PARAM_OUTINSIDECASE_ERROR(8902,"参数错误：不能委外非内催案件！"),
    OUTSOURCING_PARAM_BATCH_EXISTS(8903,"参数错误：委外批次号已存在！"),
    OUTSOURCING_CASE_IS_NULL(8904, "委外的有效案件为空！"),
    OUTSOURCING_CHANNEL_ERROR(8905, "委外渠道不存在或者无效！"),
    OUTSOURCING_MISS_BATCH(8906, "委外导出时丢失委外批次"),
    OUTSOURCING_EXPORT_FAILED(8907, "委外导出失败"),
    OUTSOURCING_FEE_RATE_ILLEGAL(8908,"委外费率信息不合法"),
    OUTSOURCING_CASE_FINISHED_OR_CANCEL(8909,"委外案件已完成或已撤回"),
    OUTSOURCING_CASE_HAS_OUTSOURCED(8910,"案件已委外"),
    OUTSOURCING_CASE_SPECIAL(8911,"特殊案件不能委外"),
    OUTSOURCING_CASE_TRANS_DATE_NOT_MATCH(8912,"转委案件时间不符"),
    OUTSOURCING_CASE_FLOW_EXCEPTION(8913,"流程异常"),


    /**
     * 回收，留案流程，撤回 73***
     */
    RECOVERY_CASE_NOT_MATCH(7300,"回收案件状态不匹配"),
    DELAY_CASE_NOT_MATCH(7301,"留案案件状态不匹配"),
    DELAY_CASE_CHANNEL_NOT_MATCH(7302,"留案案件渠道不匹配"),
    DELAY_CASE_DUMPLICATE(7303,"库中已存在与相同ID的有效案件"),
    CANCELED_STATUS_NOT_MATCH(7304,"撤回案件状态不匹配"),
    CANCELED_CASE_NOT_MATCH(7305,"非外催案件不能撤回"),




    FLOW_EXCEPTION(7399,"流程异常"),

    /**
     * 进件相关 81**
     */
    CASE_FILE_EMPTY(8100, "进件文件为空"),
    CASE_FILE_NOTMATCH(8101, "进件文件格式不符合"),
    CASE_FILE_NOTFOUND(8102, "文件不存在"),
    DUBBO_EXCEPTION(8103, "Dubbo服务调用异常"),
    NOT_GROUP_OR_MEMBER(8104, "不是组员或组长"),
    ILLEGAL_BYPASS_RATE(8105, "不合法的分件比率"),
    RECORD_BACKUP_HISTORY_ERROR(8106,"录入案件备份表失败"),
    CASE_NOT_ALLOW_IN(8107,"案件不符合准入规则"),
    CASE_HAS_EXIST(8108,"库中已存在相同贷款号的有效案件"),
    CASE_APPLY_EXCEPTION(8109,"进件系统异常"),
    CASE_NOT_FOUND(8110,"案件不存在"),
    CASE_SETTLE_TYPE_EXIST(8111,"案件批次对应的渠道关联类型有误"),
    /**
     * 信修相关 82**
     */
    UNKNOWN_ORDER_FOR_CONTACTS(8201, "未知排序类型"),
    UNKNOWN_ORDER_TYPE_FOR_CONTACTS(8202, "未知排序类型，非正序或倒序"),
    INVALID_NAME(8203, "姓名格式不符合要求"),
    INPUT_EXCEED_MAXIMUM(8204, "输入内容超过限制"),
    INVAILED_PARAM_INFOREPAIR(8205, "入参有问题（信修相关）"),
    INVAILE_CONTACT_CHANNEL(8205, "无效联系人渠道"),
    CONTACT_WITHOUT_CHANNEL(8206, "原来的联系信息没有渠道"),
    CONTACT_WITHOUT_PHONENO(8207,"没有手机号码"),
    CONTACT_NULLPOINT(8208, "信修时的空指针"),
    CONTACT_PARAM_LOSE(8209, "信修时数据丢失"),
    NO_UPLOAD_INFO(8210, "输入的信修数据为空"),
    NO_VAILED_CATEGINFO(8211,"没有正确的可录入的其他联系方式"),
    CONTACT_CONDITION_ERROR(8212,"信修数据状态有误"),
    NO_INFO_REPAIR_CASE(8213,"无可导出信修的案件"),
    LACK_THREE_ELMENTS(8214,"信修三要素缺失"),
    FAIL_UPLOAD_PARSE_INFO_REPAIR(8215,"信修转换格式录入失败"),

    /**
     * 部门相关 83**
     */
    DEPTNAME_OR_BIZNAME_IS_NULL_OR_EMPTY(8301, "小组名称或所属模块为空"),
    DEPTID_IS_NULL_OR_EMPTY(8302, "部门编号为空"),
    DEPTID_IS_INVALID(8303, "部门编号非法"),
    CREATE_DEPT_FAIL(8304, "创建小组失败,请联系管理员"),
    DELTE_DEPT_FAIL(8305, "删除小组失败,请联系管理员"),
    DEL_DEPT_FAIL(8308,"删除部门失败，请联系管理员"),
    CRE_DEPT_FAIL(8309, "创建部门失败,请联系管理员"),
    DEPTID_IS_NOT_ALLOW_DELETE(8306, "部门成员不为空，不允许删除"),
    DEPT_DONT_EXSIT(8307, "小组不存在"),
    DEPTNAME_IS_NULL_OR_EMPTY(8308, "部门名称为空"),
    DEPTNAME_EXIST(8309, "部门名称已存在"),
    DELETE_DEPARTMENT_NOT_EXIST(8310, "部门不存在!"),
    HAS_SON_DEP_NOT_ALLOW_DELETE(8311, "该部门存在子部门，不允许删除!"),
    SUP_DEPARTMENT_IS_NULL(8312, "上级部门为空"),
    NOT_SET_PASS_SHOW(8313, "请选择分件/转派是否展示"),
    NOT_SET_DEP_ROLE(8314, "请配置部门角色"),
    SUP_PASS_SHOW_WRONG(8315, "请先将上级部门设置为可分件/转派展示"),
    SON_PASS_SHOW_WRONG(8316, "无法取消分件/转派展示，因为存在可分件/转派展示的子部门"),
    CAN_NOT_DELETE_ROLE(8317, "该部门下已有 {0} 角色，无法取消，请先删除该角色用户"),
    DEPT_CANT_CREATE_THIS_ROLE(8318, "该部门不能创建该角色"),
    SUP_DEP_NO_PASS_SHOW(8319, "请先将上级部门设置为可分件/转派展示！"),
    COMPANY_HAS_NO_DEPT(8320, "该公司没有部门"),
    DEPT_DEPTH_ZERO(8321,"该部门下不能创建部门，深度不够"),
    DEPT_DEPTH_ERROR(8322,"请确认并小于上级部门的深度"),
    DEPT_DEPTH_TOO_BIG(8323,"您填写的深度太大"),
    PARAM_NULL(8324,"参数为空"),
    DEPT_MEMBER_COUNT_FULL(8325, "该部门人数已满，不可再创建用户！"),
    SUP_DEP_MEM_HAS_FULL(8326, "部门该角色设置人数超过父部门限制！"),
    PERMISSION_ERROR(8327,"自定义创建部门权限或基础创建部门权限有误"),
    DEPT_BASIC_INFO_IS_NULL(8328,"库中缺少部门基础信息，请联系管理员"),
    REMOVE_DEPT_ERROR(8329,"不能将子部门添加为父部门"),
    NO_DEPT_REMOVE(8330,"移动部门错误，没有该部门"),
    BRANCHCOMPANY_CANNOT_CONVERT_TO_DEPT(8331,"分公司不能转为部门"),
    SPCIAL_DEPT_WRONG(8332,"新进部门有误"),
    SPCIAL_USER_WRONG(8333,"新进部门账号有误"),
    /**
     * 甲方相关 84**
     */
    INVAILED_PARAM(8401, "非法入参"),
    NOT_PARTY_A_USER(8402, "非甲方用户"),
    WITHOUT_NEW_RETURN_MONEY(8403, "案件没有新的回款"),
    TIME_FILTER_WRONG(8404, "时间选择错误"),
    HAS_CASE_UPLOAD(8405, "当前已有该渠道案件正在上传"),
    RETURN_MONEY_OVER(8406,"金额超过剩余回款值"),
    RETURN_MONEY_INVALID(8407,"输入还款金额格式不符"),
    RETURN_MONEY_LOWER_THAN_ZERO(8408,"输入还款金额须大于0"),
    CASE_UPDATE_FILE_NOTMATCH(8408, "更新文件格式不符合"),
    NO_VAILED_CASES(8409, "没有正确的案件"),
    PARAM_MISSING(8410, "第{0}行,第{1}列缺失必填字段"),
    CHANNEL_ILLEGAL(8411, "第{0}行，第{1}列的案件{2}渠道权限不符"),
    PARAM_MISSING_UPDATE(8412, "案件缺失必填字段"),
    MISSING_ONLY_IDENTIFY(8413,"第{0}行，第{1}列缺失唯一标识"),
    MISSING_CASE_BATCH(8414,"第{0}行，第{1}列缺失案件批次号"),
    MISSING_UPDATE_CHANNEL(8415,"第{0}行，第{1}列缺失更新渠道"),
    CASE_UPDATE_OUTSOURCING_ATM(8416,"第{0}行，第{1}列的委案金额格式有误"),
    CASE_UPDATE_OVERDUE_ATM(8417,"第{0}行，第{1}列的逾期总额格式有误"),
    CASE_UPDATE_INTERESTS(8418,"第{0}行，第{1}列的罚滞总额格式有误"),
    CASE_UPDATE_OVERDUE_DAYS(8419,"第{0}行，第{1}列的逾期天数格式有误"),
    CASE_UPDATE_RECYCLE_AMOUNT(8420,"第{0}行,第{1}列的已还款金额格式有误"),
    UPDATE_CHANNEL_UNSAME(8421,"第{0}行,第{1}列的案件更新渠道与首行填写的不相同，请修改为一致的"),
    UPDATE_CHANNEL_UNSAME_AS_APART(8422,"第{0}行,第{1}列的案件更新渠道不是您所在的渠道，请修改"),
    NULL_UPDATE_CASE(8423,"没有案件可更新"),
    UNKNOW_CHANNEL(8424,"委案机构错误，请确保系统已存在对应渠道{0}"),
    NUMBER_FORMART_NULL(8425,"第{0}行,第{1}列的数字格式有误"),
    LOAN_ID_NOT_EXIST(8426,"根据所填的案件唯一标识、渠道和用户所在公司在已存在的案件中查询不到第{0}行的案件，请确认并修改或删除"),
    WRONG_CHANNEL(8428,"委案机构不可委案，请修改"),
    UPDATE_CHANNEL_NOT_PARTA(8429,"第{0}行,第{1}列的案件渠道不是甲方渠道，请修改"),
    NO_CHANNEL(8430,"第{0}行,第{1}列的案件渠道错误，请确保系统已存在对应渠道{2}"),
    EXCEL_PATTERN_VALIDATION_ERROR(8431,"第{0}行,第{1}列的{2}格式有误"),
    DUPLICATE_TITLE(8431,"重复的表头字段:{0},下标:{1}，请检查!"),
    REQUIRED_TITLE_NOT_FIND(8432,"缺少表头字段:{0},下标:{1}，请检查!"),
    ERROR_WITH_WORK_ID(8433,"请确认工号为{0}的人在系统中是否存在！"),
    INVAILED_REPAY_TIME(8434, "第{0}行，第{1}列的还款日期格式有误"),
    PARAM_MISSING_WITH_SHEETNO(8435,"{0}的第{1}行,第{2}列缺失必填字段"),
    EXCEL_PATTERN_VALIDATION_ERROR_WITH_SHEETNO(8436,"{0}的第{1}行,第{2}列的{3}格式有误"),
    CASE_UPDATE_REPAY_PERIOD(8437,"第{0}行,第{1}列的已还期数格式有误"),

    /**
     * 待办事项
     */
    NOT_FIND_TODO_RECORD(8456,"未找到待办记录"),
    NOT_MANAGER_USER(8457, "非经理用户"),
    TODO_RECORD_HAS_PROCESSED(8458,"该条待办记录已被处理"),


    /**
     * 分件相关 85**
     */
    NON_EXIST_USER(8501, "不存在这个用户"),
    NOT_LEADER_OR_MEMBER(8502, "不是组长或组员"),
    NOT_LEADER(8503, "不是组长"),
    INVAILED_PARAM_PROBLEM(8503, "分件时入参错误"),
    UNKNOWN_MODULE(8505, "未知模块"),
    INVAILED_STRATEGY(8506, "未知分件策略"),
    INVAILED_MODUE_FOR_DETAIN(8507, "不需要处理的滞留案件模块"),
    RULE_PRIORITY_ERROR(8508,"应用策略中优先级重复！"),
    RULE_IS_VALID_CANT_DEL(8509,"该策略不存在或者生效中，无法删除"),
    TO_USER_OR_DEPT_ERROR(8510,"分入账户与分入部门两者至少选择一个"),
    RULE_TIME_ERROR(8511,"作用周期不能为空"),
    LAST_DAY_OF_MONTH_ERROR(8512,"月末天数值必须在1-3范围内"),
    WORK_ID_NOT_FIND(8513,"工号:{0}的账户不存在!"),
    NOT_FIND_BYPASS_RULE(8514,"查询不到该分件策略!"),
    NO_SUCH_ACCOUNT(8515,"分入账户不存在"),
    ALLOT_ACCOUNT_TOO_MIX(8516,"分入账户既包含特殊账户又包含普通账户"),
    ACCOUNT_NO_AUTH(8516,"分入账户包含不能分件的账户"),






    /**
     * 案件统计 86**
     */
    UNKNOWN_STATISTICS_TYPE(8601, "未知统计类型"),
    UNKNOWN_ROLE_TYPE(8602, "未知角色类型"),
    NEED_REPAY_ATTCH_FILE(8603,"缺少还款附件"),

    /**
     * 案件统计 87**
     */
    INVAILED_NOTICE_PARAM(8701, "公告入参有误"),
    OPERATION_ILLEGAL(8702, "当前 {0} 操作正在执行中，请完成后再执行"),
    NEED_BELONG_MONTH(8703,"请选择需要查询的月份"),
    CAN_NOT_OPR_COLLECTOR(8704, "暂时无法操作涉及统计相关操作，请稍后尝试！"),
    LOSE_SYS_RULE_PARAM(8705, "缺少系统规则，请联系管理员！"),
    LOST_COLLECTOR_USER(8706, "缺少待更新统计的用户！"),
    LOST_COLLECTOR_CHANNLE(8707, "缺少待更新统计的渠道"),
    UNDEFINDED_CHANNEL(8708, "未查询到渠道！"),

    /**
     * 渠道相关 88**
     */
    INVAILED_RATE_TYPE(8801, "费率类型有误"),
    HAS_EXITS_CHANNEL_ID(8802, "渠道ID已经存在"),
    HAS_EXITS_CHANNEL_NAME(8803, "渠道名字已经存在"),
    EXITS_SAME_CHANNELS(8804, "渠道不唯一"),
    WITHOUT_COMPANY_ID(8805, "入参缺少公司id"),
    PARAM_ERROR(8806, "入参补全"),
    CHANNEL_OK_BUT_FEERATE_NOT_OK(8807,"渠道创建成功，费率初始化异常，请手动配置"),
    CAN_NOT_MODIFY_CHANNEL(8808,"渠道使用中，不可修改渠道信息"),
    CHANNEL_ID_ERROR(8809,"渠道代号不能包含-！"),


    /**
     * 拨号相关
     */
    CALL_PHONE_NULL(7000, "拨打的号码不能为空"),
    CALL_CHECK_ERROR(7001, "拨号出现异常"),
    CALL_USER_DATA_IS_NULL_OR_EMPTY(7002, "通话ID为空"),
    CALL_DATE_IS_NULL_OR_EMPTY(7003, "通话日期为空"),
    CALL_AGENT_NO_IS_NULL_OR_EMPTY(7004, "坐席号为空"),
    CALL_SESSIONID_IS_EMPTY(7005, "云呼未推送通话信息"),
    PARAMETER_NULL(7006, "参数不能为空"),
    RECORD_MP3_NULL(7007, "暂时没有该录音，请稍后再看~"),
    CALL_CENTER_SERVICE_ERROR(7008, "云呼服务异常"),
    NO_PERMISSION(7009, "抱歉，您没有权限拨号~"),
    INVALID_PHONENO(7010, "无效的手机号码"),
    CALL_DIALING(7011, "正在拨号中，请稍后重播~"),
    CALL_CONTACTINFO_NOT_EXIST(7012, "联系人信息不存在~"),
    CALL_CASE_NO_NULL(7013, "案件编号不能为空"),
    NO_AGENTNO(7014, "抱歉，您没有坐席号，请联系管理员~"),
    MESSAGE_SEND_ERROR(7016, "该号码短信发送过于频繁，请稍后再试"),
    CODE_FAULT(7017,"公司代号应为数字或英文字母组成的四位字符"),
    CODE_EXIST(7018,"公司代号已存在"),
    COMPANY_NAME_EXIST(7019,"公司名已存在"),
    CODE_NULL(7020,"公司代号必填"),
    COMAPNY_NAME_NULL(7021,"公司名必填"),
    NO_RING_OFF(7022,"该类型拨号不支持挂断功能，请从软电话挂断！"),
    CASE_STATUS_ERROR(7023,"不能对已完成/已撤回案件进行外呼操作"),
    DIAL_ITEM_NULL(7024,"质检录入总表无数据"),
    ERROR_START_TIME(7025,"缺失通话起始时间或起始时间格式不正确！"),
    NO_SEAT_ALLOW_FLOW(7026,"查无此坐席号对应的坐席分配流水!"),
    /**
     * 发送短信相关 71**
     */
    NOT_HAVE_CALLINFO(7101, "该用户没有坐席"),


    /**
     * 某数据源信修相关 72**
     */
    NULL_RESPONSE(7201, "回应为空"),
    MOU_DATA_ERROR(7202, "调用某数据源报错"),

    /**
     * 工单相关 73**
     */
    REPEAT_PARAM_WHEN_ACCOUNT(7401, "核账出现重复参数"),
    EMPTY_SHEET(7402, "上传文件存在空页"),
    CASE_NUM_ZERO(7403, "库中该记录不存在案件,关键字：{0},渠道：{1}"),
    FAILED_TO_PARSE_FILE(7404, "文件解析失败"),
    INVAILED_DATE_PATTEM(7405, "文件中的日期格式不对"),
    ORDER_ID_NOT_NULL(7405, "工单号不能为空"),
    SUBMIT_NOT_NULL(7406,"本次提交无效，请更改工单状态或添加备注"),
    WORK_ORDER_IS_END(7407,"该工单已结单或撤回或驳回，无法提交"),
    NOT_VALID_STATUS(7408,"您没有权限设置该工单状态"),
    NO_VALID_USER(7409,"您没有权限提交该工单"),
    ONLY_SUBMIT_CANCEL(7410,"该工单状态无法撤销"),
    ONLY_SUBMIT_END(7411,"该工单状态无法结单"),
    SUBMIT_STATUS_INVALID(7411,"您提交的工单状态无效"),
    HAS_NO_HANDLE_LIST(7412,"存在未处理的申请记录，无法更新待确认结单状态"),
    TIME_NEED_START_AND_END(7413,"起始时间必须都填或都不填"),
    NOT_FUND_ORDER(7414,"查询不到工单"),
    ACCOUNT_FILE_IS_EMPTY(7415, "上传文件为空"),
    LOSE_ID_AND_LOAN_ID(7416, "丢失身份证号和贷款号"),
    MUST_CONTAINS_MONEY_DATE_TYPE(7417, "上传文件必须包含金额，时间"),
    REPEAT_CASES(7418, "文件中存在重复案件"),
    NO_VALID_CHANNEL_USER(7419,"该渠道负责人没有权限处理该工单"),
    VALID_CHANNEL_USER_NOT_FOUNT(7420,"该渠道没有设置处理人"),
    CASE_NUM_MORE_THAN_ONE(7421, "库中该记录查询{0}个案件,关键字：{1},渠道：{2}"),
    INVOLVED_CASE_NOT_FOUND(7422,"案件{0}没有找到，请核查"),
    WO_HANDLER_TOO_MUCH(7423,"提报人不能超过20个"),
    LOAN_ID_TOO_MUCH(7424,"贷款编号不能超过100个"),
    CASE_NOT_EXIST(7425,"编号{0}的案件不存在"),
    CASE_LENDING_INSTITUTION_IS_NULL(7426,"编号{0}的案件渠道为空"),
    CASE_LENDING_INSTITUTION_NOT_SAME(7427,"工单关联案件渠道需一致"),
    NOT_FIND_TEMPLATE_URL(7428,"未找到对应的模板"),
    NO_CASE_HANDLER(7429,"没有选择工单处理人"),
    NO_WO_CHANNEL(7430,"没有选择工单渠道"),
    EMPTY_FILE(7431,"上传文件内容为空"),
    WO_COMPANY_NOT_MATCH(7432,"工单提交内容公司权限不符"),
    WO_CASE_NOT_MATCH(7433,"用户没有对案件 {0} 的操作权限"),
    WO_CHANNEL_NOT_MATCH(7434,"用户没有对{0}渠道案件的操作权限"),
    WO_NOT_MATCH(7435,"工单提交内容不合法"),
    CASE_HANDLER_ERROR(7436,"非本人案件无法提报工单，案件编号{0}"),
    BIG_MAN_ERROR(7437,"指定用户不存在或无分件转派权限"),
    USER_NO_PERMISSION_TO_COMMIT(7438,"该用户无权提报带有workid的核账工单"),
    CASE_HANDLE_NOT_FOUND(7439,"该还款时间内未找到案件{0}处理人"),
    CASE_HANDLER_SUBMITTER_NOT_SAME(7440,"提报人与案件{0}处理人不一致"),
    FINISH_OR_RECALL_CASE_NOT_ALLOT(7441,"已完成或已撤回的案件无法留案,贷款编号{0}"),
    CHANNEL_NOT_MATCH(7442,"案件{0}渠道与所选渠道不一致"),
    LOAN_AND_IDENTITY_NOT_SAME(7443,"身份证与贷款编号{0}不一致"),
    AGREE_REPAY_TIME_IS_ERROR(7445,"案件{0}承诺还款时间必须大于等于今天"),
    WO_PARSH_ERROR(7446,"工单指标统计错误,统计数量小于两个"),
    /**
     * 刷新号码状态 75**
     */
    FAILED_TO_PARSE_PHONE_STATUS_FILE(7501,"文件解析失败"),
    HAS_NO_VAILED_PHONE_STATUS(7502,"没有符合要求的号码状态"),
    FAILED_TO_DOWNLOAD_PHONE_UPDATE_FILE(7503,"号码更新文档下载失败"),

    /**
     * 操作管理相关 76**
     */
    DOWNLOAD_FAILED(7601, "文件下载失败"),
    FAILED_PARSE_FILE_WHEN_BATCH_TURN_CASE(7602, "批量转派时解析文件失败"),

    /**
     * 模板相关77**
     */
    TEMPLATEM_NAME_CODE_ERROR(7701,"文件名编/解码失败"),
    TEMPLATEM_NAME_FORMAT_ERROR(7702,"文件名格式错误"),
    TEMP_NAME_HAS_EXIST(7703,"已存在同名模板"),
    TEMP_DOWNLOAD_FAIL(7704,"模板下载失败"),
    TEMPLATEM_NAME_DOUBLE(7705,"该模板名已被其地方使用"),
    /**
     * BLOCKCODE相关 90**
     */
    BLOCKCOED_PERMISSION_ERROR(9000, "权限错误"),
    BLOCKCOED_EXSIT_ERROR(9001, "不能更新blockcode状态"),
    BLOCKCOED_NULL_ERROR(9002, "blockcode数据为空"),

    /**
     * 商账相关 91
     */
    DISCUSSAOUNT_ERROR(9100,"远程服务调用失败"),

    /**
     * 坐席相关 60**
     */
    CALL_AGNETNO_NULL(6000, "坐席号不能为空"),
    CALL_GROUPID_NULL(6001, "坐席组号不能为空"),
    CALL_PWD_NULL(6002, "坐席密码不能为空"),
    CALL_PAGE_NULL(6003, "当前页或每页显示条数不能为空"),
    CALL_NO_PERMISSION(6004, "抱歉，您没有权限管理坐席号"),
    CALL_LENGTH_GROUP_ERROR(7014, "最大输入不能超过10个"),
    CALL_LENGTH_PWD_ERROR(7015, "最大输入不能超过50个"),
    CALL_GROUP_EXISTS(6005, "抱歉，坐席组和坐席号已存在"),
    CALL_ALLOTAGENT_ERROR(6006, "抱歉，分配坐席失败"),
    CALL_UNBIND_ERROR(6007, "抱歉，解綁失败"),
    CALL_AGENT_DELETE_FAILED(6008, "删除坐席失败"),
    FAILED_TO_GET_SEATS(6009, "获得坐席失败"),
    PARAM_MISS(6010, "参数丢失"),
    FAILED_ALLOT_SEATS_TO_COMPANY(6011, "分配给公司坐席失败"),
    NUM_NOT_MATCH(6012, "坐席查询数量不匹配"),
    CALL_WAY_NULL(6013, "拨号方式不能为空"),
    TIME_FORMAT_ERROR(6014, "还款时间格式不正确"),
    TIME_OUT_ERROR(6019, "还款时间不能大于当前时间"),
    RECEIVABLEAMT_FORMAT_ERROR(6015, "实收金额格式不正确"),
    REPAYAMT_FORMAT_ERROR(6016, "还款金额格式不正确"),
    NOT_FIND_REPAY_RECORD(6017, "还款流水号不存在"),
    NOT_FIND_CHECK_ACCOUNT(6018,"工单还款流水不存在"),
    CHECK_MONEY_FORMAT_ERROR(6019, "入账金额格式不正确"),
    DATE_ERROR(7001,"时间不能大于今天"),
    NO_MODIFY_FIND(6018, "没有找到修改项，请修改相关字段后提交"),
    CALL_INFO_NULL(6019,"没有符合要求的坐席信息"),
    DIAL_WAY_NULL(6020,"请选择坐席类型"),
    /**
     * 质检相关
     */
    QUALITY_TARGET_DEPID_DUPLICAT(8000,"该部门已有产能要求"),
    QUALITY_TARGET_NO_UPDATE(8001,"需更新的部门产能要求为空"),
    QUALITY_DEPID_OR_FLAG_NULL(8002,"部门id或者通量标识为空"),
    QUALITY_TARGET_IS_NOT_LEGAL(8020,"部门产能输入不合法"),
    QUALITY_TARGET_NOT_FOUND(8021,"未找到相应的产能数据"),
    TARGET_OR_TIME_ERROR(8022,"产能或首拨时间至少选填一个，且产能需同时设置或不设置，首拨时间也需同时设置或不设置"),
    TARGET_NEED_BIGGER_THAN_SUCCESS_TARGET(8023,"有效产能目标不能大于产能目标"),
    DAY_TARGET_BIGGER_THAN_WEEK_TARGET(8024,"日产能不能大于周产能"),
    MORNING_TIME_BIGGER_THAN_AFTERNOON_TIME(8025,"上午首拨时间不应大于下午首拨时间"),
    /**
     * 权限相关
     */
    NO_WEB_PAGE(9201, "无此父页面"),
    FATHER_PAGE_ERROR(9202, "选择的父页面超过两层"),
    FATHER_ID_IS_NULL(9203, "父页面Id为空"),
    RANGE_PARAM_ERROR(9204, "权限范围参数错误"),
    COMPONENT_PARAM_IS_NULL(9205, "COMPONENT参数缺失"),
    FATHER_PAGE_DISABLED(9206, "不可用的父页面"),
    DUMPLICATE_SYS_CODE(9207, "已存在的权限CODE"),
    DUMPLICATE_COMPONENT_NAME(9208, "已存在的COMPONENT名"),
    CANNOT_DELETE_ROLE(9209,"该角色有用户使用，无法删除"),

    /**
     * 结佣相关
     */
    NO_CASE_FOR(9300,"无对应案件"),
    NO_RULE_FOR(9301,"无可用于分组的规则"),
    /**
     * 录音相关
     */

    DIALING_QUERY_NO_RANGE(9400,"录音查询超出权限范围"),
    SOUND_NORMAL_REMARKS(9401,"没有添加通用备注的权限");

    private Integer respCode;
    private String respMsg;

    ErrorEnum(Integer respCode) {
        this.respCode = respCode;
    }

    public Integer getRespCode() {
        return respCode;
    }


    ErrorEnum(Integer respCode, String respMsg) {
        this(respCode);
        this.respMsg = respMsg;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public static ErrorEnum getErrorEnum(Integer respCode){
        if (respCode == null){
            return null;
        }
        for (ErrorEnum errorEnum : ErrorEnum.values()){
            if (errorEnum.getRespCode().equals(respCode)){
                return errorEnum;
            }
        }
        return SYSTEM_UNKONW_ERROR;
    }
}
