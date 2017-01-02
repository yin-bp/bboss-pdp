delete from td_sm_parameters where dict_id in (select dict_id from td_sm_dict where dict_status = '0')
delete from td_sm_dict where  dict_status = '0'