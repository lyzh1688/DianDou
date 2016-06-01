/**
 * Created by 胡志洁 on 2016/5/9.
 */
angular.module('diandou')
  .constant('REMOTE_SERVER', {
    remoteDiandouSrv: 'http://192.168.1.5:8080/diandou',
  })
  .constant('AUTH_STATUS', {
    login_pass: 'login_pass',
    login_fail:'login_fail',
    logout:'logout',

    reg_failed_mobile_exists:'reg_failed_mobile_exists',
    reg_failed_username_exists:'reg_failed_username_exists',
    reg_success:'reg_success',

  })
  .constant('CONFIG',{
    role_id:'1',
    adv_type:'5',
  })
