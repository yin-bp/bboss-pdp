<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/inc/tld.inc"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="Preview page of Metronic Admin Theme #1 for 404 page option 2" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        
        
    <%@ include file="/jsp/inc/css-link-metronic.inc"%>
	
	
</head>

<body class="gray-bg page-404-full-page ">
<div class="row wrapper border-bottom white-bg sdp-head">
    <div class="col-sm-12">
        <ol class="breadcrumb">
            <li>位置: 系统管理</li>
            <li>应用接入管理</li>
        </ol>
    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox">
                 
                <div class="page-content-wrapper">
                    <!-- BEGIN CONTENT BODY -->
                    <div class="page-content">
                          <div class="row">
                             
                            <div class="col-md-12 ">
                                <!-- BEGIN SAMPLE FORM PORTLET-->
                                <div class="portlet light bordered">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="icon-settings font-dark"></i>
                                            <span class="caption-subject font-dark sbold uppercase">Horizontal Form</span>
                                        </div>
                                        <div class="actions">
                                            <div class="btn-group btn-group-devided" data-toggle="buttons">
                                                <label class="btn btn-transparent dark btn-outline btn-circle btn-sm active">
                                                    <input type="radio" name="options" class="toggle" id="option1">Actions</label>
                                                <label class="btn btn-transparent dark btn-outline btn-circle btn-sm">
                                                    <input type="radio" name="options" class="toggle" id="option2">Settings</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="portlet-body form">
                                        <form class="form-horizontal" role="form">
                                            <div class="form-body">
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Block Help</label>
                                                    <div class="col-md-9">
                                                        <input type="text" class="form-control" placeholder="Enter text">
                                                        <span class="help-block"> A block of help text. </span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Inline Help</label>
                                                    <div class="col-md-9">
                                                        <input type="text" class="form-control input-inline input-medium" placeholder="Enter text">
                                                        <span class="help-inline"> Inline help. </span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Input Group</label>
                                                    <div class="col-md-9">
                                                        <div class="input-inline input-medium">
                                                            <div class="input-group">
                                                                <span class="input-group-addon">
                                                                    <i class="fa fa-user"></i>
                                                                </span>
                                                                <input type="email" class="form-control" placeholder="Email Address"> </div>
                                                        </div>
                                                        <span class="help-inline"> Inline help. </span>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Email Address</label>
                                                    <div class="col-md-9">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-envelope"></i>
                                                            </span>
                                                            <input type="email" class="form-control" placeholder="Email Address"> </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Password</label>
                                                    <div class="col-md-9">
                                                        <div class="input-group">
                                                            <input type="password" class="form-control" placeholder="Password">
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-user"></i>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Left Icon</label>
                                                    <div class="col-md-9">
                                                        <div class="input-icon">
                                                            <i class="fa fa-bell-o"></i>
                                                            <input type="text" class="form-control" placeholder="Left icon"> </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Right Icon</label>
                                                    <div class="col-md-9">
                                                        <div class="input-icon right">
                                                            <i class="fa fa-microphone"></i>
                                                            <input type="text" class="form-control" placeholder="Right icon"> </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Icon Input in Group Input</label>
                                                    <div class="col-md-9">
                                                        <div class="input-group">
                                                            <div class="input-icon">
                                                                <i class="fa fa-lock fa-fw"></i>
                                                                <input id="newpassword" class="form-control" type="text" name="password" placeholder="password" /> </div>
                                                            <span class="input-group-btn">
                                                                <button id="genpassword" class="btn btn-success" type="button">
                                                                    <i class="fa fa-arrow-left fa-fw" /></i> Random</button>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Input With Spinner</label>
                                                    <div class="col-md-9">
                                                        <input type="password" class="form-control spinner" placeholder="Password"> </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Static Control</label>
                                                    <div class="col-md-9">
                                                        <p class="form-control-static"> email@example.com </p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Disabled</label>
                                                    <div class="col-md-9">
                                                        <input type="password" class="form-control" placeholder="Disabled" disabled> </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Readonly</label>
                                                    <div class="col-md-9">
                                                        <input type="password" class="form-control" placeholder="Readonly" readonly> </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Dropdown</label>
                                                    <div class="col-md-9">
                                                        <select class="form-control">
                                                            <option>Option 1</option>
                                                            <option>Option 2</option>
                                                            <option>Option 3</option>
                                                            <option>Option 4</option>
                                                            <option>Option 5</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Multiple Select</label>
                                                    <div class="col-md-9">
                                                        <select multiple class="form-control">
                                                            <option>Option 1</option>
                                                            <option>Option 2</option>
                                                            <option>Option 3</option>
                                                            <option>Option 4</option>
                                                            <option>Option 5</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Textarea</label>
                                                    <div class="col-md-9">
                                                        <textarea class="form-control" rows="3"></textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="exampleInputFile" class="col-md-3 control-label">File input</label>
                                                    <div class="col-md-9">
                                                        <input type="file" id="exampleInputFile">
                                                        <p class="help-block"> some help text here. </p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Checkboxes</label>
                                                    <div class="col-md-9">
                                                        <div class="mt-checkbox-list">
                                                            <label class="mt-checkbox">
                                                                <input type="checkbox"> Checkbox 1
                                                                <span></span>
                                                            </label>
                                                            <label class="mt-checkbox">
                                                                <input type="checkbox"> Checkbox 1
                                                                <span></span>
                                                            </label>
                                                            <label class="mt-checkbox mt-checkbox-disabled">
                                                                <input type="checkbox" disabled> Disabled
                                                                <span></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Outline Checkboxes</label>
                                                    <div class="col-md-9">
                                                        <div class="mt-checkbox-list">
                                                            <label class="mt-checkbox mt-checkbox-outline">
                                                                <input type="checkbox"> Checkbox 1
                                                                <span></span>
                                                            </label>
                                                            <label class="mt-checkbox mt-checkbox-outline">
                                                                <input type="checkbox"> Checkbox 1
                                                                <span></span>
                                                            </label>
                                                            <label class="mt-checkbox mt-checkbox-outline mt-checkbox-disabled">
                                                                <input type="checkbox" disabled> Disabled
                                                                <span></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Inline Checkboxes</label>
                                                    <div class="col-md-9">
                                                        <div class="mt-checkbox-inline">
                                                            <label class="mt-checkbox">
                                                                <input type="checkbox" id="inlineCheckbox21" value="option1"> Checkbox 1
                                                                <span></span>
                                                            </label>
                                                            <label class="mt-checkbox">
                                                                <input type="checkbox" id="inlineCheckbox22" value="option2"> Checkbox 2
                                                                <span></span>
                                                            </label>
                                                            <label class="mt-checkbox mt-checkbox-disabled">
                                                                <input type="checkbox" id="inlineCheckbox23" value="option3" disabled> Disabled
                                                                <span></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Radios</label>
                                                    <div class="col-md-9">
                                                        <div class="mt-radio-list">
                                                            <label class="mt-radio">
                                                                <input type="radio" name="optionsRadios" id="optionsRadios22" value="option1" checked> Option 1
                                                                <span></span>
                                                            </label>
                                                            <label class="mt-radio">
                                                                <input type="radio" name="optionsRadios" id="optionsRadios23" value="option2" checked> Option 2
                                                                <span></span>
                                                            </label>
                                                            <label class="mt-radio mt-radio-disabled">
                                                                <input type="radio" name="optionsRadios" id="optionsRadios24" value="option2" disabled> Disabled
                                                                <span></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Outline Radios</label>
                                                    <div class="col-md-9">
                                                        <div class="mt-radio-list">
                                                            <label class="mt-radio mt-radio-outline">
                                                                <input type="radio" name="optionsRadios" id="optionsRadios22" value="option1" checked> Option 1
                                                                <span></span>
                                                            </label>
                                                            <label class="mt-radio mt-radio-outline">
                                                                <input type="radio" name="optionsRadios" id="optionsRadios23" value="option2" checked> Option 2
                                                                <span></span>
                                                            </label>
                                                            <label class="mt-radio mt-radio-outline mt-radio-disabled">
                                                                <input type="radio" name="optionsRadios" id="optionsRadios24" value="option2" disabled> Disabled
                                                                <span></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Inline Radio</label>
                                                    <div class="col-md-9">
                                                        <div class="mt-radio-inline">
                                                            <label class="mt-radio">
                                                                <input type="radio" name="optionsRadios" id="optionsRadios25" value="option1" checked> Option 1
                                                                <span></span>
                                                            </label>
                                                            <label class="mt-radio">
                                                                <input type="radio" name="optionsRadios" id="optionsRadios26" value="option2" checked> Option 2
                                                                <span></span>
                                                            </label>
                                                            <label class="mt-radio mt-radio-disabled">
                                                                <input type="radio" name="optionsRadios" id="optionsRadios27" value="option3" disabled> Disabled
                                                                <span></span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-actions">
                                                <div class="row">
                                                    <div class="col-md-offset-3 col-md-9">
                                                        <button type="submit" class="btn green">Submit</button>
                                                        <button type="button" class="btn default">Cancel</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!-- END SAMPLE FORM PORTLET-->
                                <!-- BEGIN SAMPLE FORM PORTLET-->
                                <div class="portlet box purple ">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-gift"></i> Horizontal Form Height Sizing </div>
                                        <div class="tools">
                                            <a href="" class="collapse"> </a>
                                            <a href="#portlet-config" data-toggle="modal" class="config"> </a>
                                            <a href="" class="reload"> </a>
                                            <a href="" class="remove"> </a>
                                        </div>
                                    </div>
                                    <div class="portlet-body form">
                                        <form class="form-horizontal" role="form">
                                            <div class="form-body">
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Large Input</label>
                                                    <div class="col-md-9">
                                                        <input type="text" class="form-control input-lg" placeholder="Large Input"> </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Default Input</label>
                                                    <div class="col-md-9">
                                                        <input type="text" class="form-control" placeholder="Default Input"> </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Small Input</label>
                                                    <div class="col-md-9">
                                                        <input type="text" class="form-control input-sm" placeholder="Default Input"> </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Large Select</label>
                                                    <div class="col-md-9">
                                                        <select class="form-control input-lg">
                                                            <option>Option 1</option>
                                                            <option>Option 2</option>
                                                            <option>Option 3</option>
                                                            <option>Option 4</option>
                                                            <option>Option 5</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Default Select</label>
                                                    <div class="col-md-9">
                                                        <select class="form-control">
                                                            <option>Option 1</option>
                                                            <option>Option 2</option>
                                                            <option>Option 3</option>
                                                            <option>Option 4</option>
                                                            <option>Option 5</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">Small Select</label>
                                                    <div class="col-md-9">
                                                        <select class="form-control input-sm">
                                                            <option>Option 1</option>
                                                            <option>Option 2</option>
                                                            <option>Option 3</option>
                                                            <option>Option 4</option>
                                                            <option>Option 5</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-actions right1">
                                                <button type="button" class="btn default">Cancel</button>
                                                <button type="submit" class="btn green">Submit</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!-- END SAMPLE FORM PORTLET-->
                                <!-- BEGIN SAMPLE FORM PORTLET-->
                                <div class="portlet box purple ">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-gift"></i> Fluid Input Groups </div>
                                        <div class="tools">
                                            <a href="" class="collapse"> </a>
                                            <a href="#portlet-config" data-toggle="modal" class="config"> </a>
                                            <a href="" class="reload"> </a>
                                            <a href="" class="remove"> </a>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <h4 class="block">Checkboxe Addons</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">
                                                            <input type="checkbox">
                                                            <span></span>
                                                        </span>
                                                        <input type="text" class="form-control"> </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control">
                                                        <span class="input-group-addon">
                                                            <input type="checkbox"> </span>
                                                    </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                            <!-- /.row -->
                                        </form>
                                        <h4 class="block">Button Addons</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <span class="input-group-btn">
                                                            <button class="btn red" type="button">Go!</button>
                                                        </span>
                                                        <input type="text" class="form-control"> </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control">
                                                        <span class="input-group-btn">
                                                            <button class="btn blue" type="button">Go!</button>
                                                        </span>
                                                    </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                            <!-- /.row -->
                                        </form>
                                        <h4 class="block">Button Addons On Both Sides</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="input-group">
                                                        <span class="input-group-btn">
                                                            <button class="btn red" type="button">Go!</button>
                                                        </span>
                                                        <input type="text" class="form-control">
                                                        <span class="input-group-btn">
                                                            <button class="btn blue" type="button">Go!</button>
                                                        </span>
                                                    </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                        </form>
                                        <h4 class="block">Buttons With Dropdowns</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn green dropdown-toggle" data-toggle="dropdown">Action
                                                                <i class="fa fa-angle-down"></i>
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li>
                                                                    <a href="javascript:;"> Action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Another action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Something else here </a>
                                                                </li>
                                                                <li class="divider"> </li>
                                                                <li>
                                                                    <a href="javascript:;"> Separated link </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <!-- /btn-group -->
                                                        <input type="text" class="form-control"> </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control">
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn yellow dropdown-toggle" data-toggle="dropdown">Action
                                                                <i class="fa fa-angle-down"></i>
                                                            </button>
                                                            <ul class="dropdown-menu pull-right">
                                                                <li>
                                                                    <a href="javascript:;"> Action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Another action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Something else here </a>
                                                                </li>
                                                                <li class="divider"> </li>
                                                                <li>
                                                                    <a href="javascript:;"> Separated link </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <!-- /btn-group -->
                                                    </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                            <!-- /.row -->
                                        </form>
                                        <h4 class="block">Buttons With Dropdowns On Both Sides</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn green dropdown-toggle" data-toggle="dropdown">Action
                                                                <i class="fa fa-angle-down"></i>
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li>
                                                                    <a href="javascript:;"> Action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Another action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Something else here </a>
                                                                </li>
                                                                <li class="divider"> </li>
                                                                <li>
                                                                    <a href="javascript:;"> Separated link </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <!-- /btn-group -->
                                                        <input type="text" class="form-control">
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn yellow dropdown-toggle" data-toggle="dropdown">Action
                                                                <i class="fa fa-angle-down"></i>
                                                            </button>
                                                            <ul class="dropdown-menu pull-right">
                                                                <li>
                                                                    <a href="javascript:;"> Action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Another action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Something else here </a>
                                                                </li>
                                                                <li class="divider"> </li>
                                                                <li>
                                                                    <a href="javascript:;"> Separated link </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <!-- /btn-group -->
                                                    </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                            <!-- /.row -->
                                        </form>
                                        <h4 class="block">Segmented Buttons</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn default" tabindex="-1">Action</button>
                                                            <button type="button" class="btn default dropdown-toggle" data-toggle="dropdown" tabindex="-1">
                                                                <i class="fa fa-angle-down"></i>
                                                            </button>
                                                            <ul class="dropdown-menu" role="menu">
                                                                <li>
                                                                    <a href="javascript:;"> Action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Another action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Something else here </a>
                                                                </li>
                                                                <li class="divider"> </li>
                                                                <li>
                                                                    <a href="javascript:;"> Separated link </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <input type="text" class="form-control"> </div>
                                                    <!-- /.input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control">
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn green" tabindex="-1">Action</button>
                                                            <button type="button" class="btn green dropdown-toggle" data-toggle="dropdown" tabindex="-1">
                                                                <i class="fa fa-angle-down"></i>
                                                            </button>
                                                            <ul class="dropdown-menu pull-right" role="menu">
                                                                <li>
                                                                    <a href="javascript:;"> Action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Another action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Something else here </a>
                                                                </li>
                                                                <li class="divider"> </li>
                                                                <li>
                                                                    <a href="javascript:;"> Separated link </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <!-- /.input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!-- END SAMPLE FORM PORTLET-->
                                <!-- BEGIN SAMPLE FORM PORTLET-->
                                <div class="portlet box purple ">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-gift"></i> Fixed Input Groups </div>
                                        <div class="tools">
                                            <a href="" class="collapse"> </a>
                                            <a href="#portlet-config" data-toggle="modal" class="config"> </a>
                                            <a href="" class="reload"> </a>
                                            <a href="" class="remove"> </a>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <h4 class="block">Checkboxe Addons</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="input-group input-medium">
                                                        <span class="input-group-addon">
                                                            <input type="checkbox"> </span>
                                                        <input type="text" class="form-control" placeholder=".input-medium"> </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                                <div class="col-md-6">
                                                    <div class="input-group input-medium">
                                                        <input type="text" class="form-control" placeholder=".input-medium">
                                                        <span class="input-group-addon">
                                                            <input type="checkbox"> </span>
                                                    </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                            <!-- /.row -->
                                        </form>
                                        <h4 class="block">Button Addons</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="input-group input-medium">
                                                        <span class="input-group-btn">
                                                            <button class="btn red" type="button">Go!</button>
                                                        </span>
                                                        <input type="text" class="form-control" placeholder=".input-medium"> </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                                <div class="col-md-6">
                                                    <div class="input-group input-medium">
                                                        <input type="text" class="form-control" placeholder=".input-medium">
                                                        <span class="input-group-btn">
                                                            <button class="btn blue" type="button">Go!</button>
                                                        </span>
                                                    </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                            <!-- /.row -->
                                        </form>
                                        <h4 class="block">Button Addons On Both Sides</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="input-group input-large">
                                                        <span class="input-group-btn">
                                                            <button class="btn red" type="button">Go!</button>
                                                        </span>
                                                        <input type="text" class="form-control" placeholder=".input-large">
                                                        <span class="input-group-btn">
                                                            <button class="btn blue" type="button">Go!</button>
                                                        </span>
                                                    </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                        </form>
                                        <h4 class="block">Buttons With Dropdowns</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="input-group input-medium">
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn green dropdown-toggle" data-toggle="dropdown">Action
                                                                <i class="fa fa-angle-down"></i>
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li>
                                                                    <a href="javascript:;"> Action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Another action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Something else here </a>
                                                                </li>
                                                                <li class="divider"> </li>
                                                                <li>
                                                                    <a href="javascript:;"> Separated link </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <!-- /btn-group -->
                                                        <input type="text" class="form-control" placeholder=".input-medium"> </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                                <div class="col-md-6">
                                                    <div class="input-group input-medium">
                                                        <input type="text" class="form-control" placeholder=".input-medium">
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn yellow dropdown-toggle" data-toggle="dropdown">Action
                                                                <i class="fa fa-angle-down"></i>
                                                            </button>
                                                            <ul class="dropdown-menu pull-right">
                                                                <li>
                                                                    <a href="javascript:;"> Action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Another action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Something else here </a>
                                                                </li>
                                                                <li class="divider"> </li>
                                                                <li>
                                                                    <a href="javascript:;"> Separated link </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <!-- /btn-group -->
                                                    </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                            <!-- /.row -->
                                        </form>
                                        <h4 class="block">Buttons With Dropdowns On Both Sides</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="input-group input-xlarge">
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn green dropdown-toggle" data-toggle="dropdown">Action
                                                                <i class="fa fa-angle-down"></i>
                                                            </button>
                                                            <ul class="dropdown-menu">
                                                                <li>
                                                                    <a href="javascript:;"> Action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Another action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Something else here </a>
                                                                </li>
                                                                <li class="divider"> </li>
                                                                <li>
                                                                    <a href="javascript:;"> Separated link </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <!-- /btn-group -->
                                                        <input type="text" class="form-control" placeholder=".input-xlarge">
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn yellow dropdown-toggle" data-toggle="dropdown">Action
                                                                <i class="fa fa-angle-down"></i>
                                                            </button>
                                                            <ul class="dropdown-menu pull-right">
                                                                <li>
                                                                    <a href="javascript:;"> Action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Another action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Something else here </a>
                                                                </li>
                                                                <li class="divider"> </li>
                                                                <li>
                                                                    <a href="javascript:;"> Separated link </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <!-- /btn-group -->
                                                    </div>
                                                    <!-- /input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                            <!-- /.row -->
                                        </form>
                                        <h4 class="block">Segmented Buttons</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="input-group input-large">
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn default" tabindex="-1">Action</button>
                                                            <button type="button" class="btn default dropdown-toggle" data-toggle="dropdown" tabindex="-1">
                                                                <i class="fa fa-angle-down"></i>
                                                            </button>
                                                            <ul class="dropdown-menu" role="menu">
                                                                <li>
                                                                    <a href="javascript:;"> Action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Another action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Something else here </a>
                                                                </li>
                                                                <li class="divider"> </li>
                                                                <li>
                                                                    <a href="javascript:;"> Separated link </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <input type="text" class="form-control" placeholder=".input-large"> </div>
                                                    <!-- /.input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                        </form>
                                        <form role="form" class="margin-top-10">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="input-group input-large">
                                                        <input type="text" class="form-control" placeholder=".input-large">
                                                        <div class="input-group-btn">
                                                            <button type="button" class="btn green" tabindex="-1">Action</button>
                                                            <button type="button" class="btn green dropdown-toggle" data-toggle="dropdown" tabindex="-1">
                                                                <i class="fa fa-angle-down"></i>
                                                            </button>
                                                            <ul class="dropdown-menu" role="menu">
                                                                <li>
                                                                    <a href="javascript:;"> Action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Another action </a>
                                                                </li>
                                                                <li>
                                                                    <a href="javascript:;"> Something else here </a>
                                                                </li>
                                                                <li class="divider"> </li>
                                                                <li>
                                                                    <a href="javascript:;"> Separated link </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <!-- /.input-group -->
                                                </div>
                                                <!-- /.col-md-6 -->
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!-- END SAMPLE FORM PORTLET-->
                                <div class="portlet box blue ">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-gift"></i> Validation States </div>
                                        <div class="tools">
                                            <a href="" class="collapse"> </a>
                                            <a href="#portlet-config" data-toggle="modal" class="config"> </a>
                                            <a href="" class="reload"> </a>
                                            <a href="" class="remove"> </a>
                                        </div>
                                    </div>
                                    <div class="portlet-body form">
                                        <form role="form">
                                            <div class="form-body">
                                                <div class="form-group has-success">
                                                    <label class="control-label">Input with success</label>
                                                    <input type="text" class="form-control" id="inputSuccess"> </div>
                                                <div class="form-group has-warning">
                                                    <label class="control-label">Input with warning</label>
                                                    <input type="text" class="form-control" id="inputWarning"> </div>
                                                <div class="form-group has-error">
                                                    <label class="control-label">Input with error</label>
                                                    <input type="text" class="form-control" id="inputError"> </div>
                                            </div>
                                            <div class="form-actions">
                                                <button type="button" class="btn default">Cancel</button>
                                                <button type="submit" class="btn red">Submit</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="portlet box yellow ">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-gift"></i> Validation States With Icons </div>
                                        <div class="tools">
                                            <a href="" class="collapse"> </a>
                                            <a href="#portlet-config" data-toggle="modal" class="config"> </a>
                                            <a href="" class="reload"> </a>
                                            <a href="" class="remove"> </a>
                                        </div>
                                    </div>
                                    <div class="portlet-body form">
                                        <form role="form">
                                            <div class="form-body">
                                                <div class="form-group">
                                                    <label class="control-label">Default input</label>
                                                    <div class="input-icon right">
                                                        <i class="fa fa-info-circle tooltips" data-original-title="Email address" data-container="body"></i>
                                                        <input type="text" class="form-control"> </div>
                                                </div>
                                                <div class="form-group has-success">
                                                    <label class="control-label">Input with success</label>
                                                    <div class="input-icon right">
                                                        <i class="fa fa-check tooltips" data-original-title="You look OK!" data-container="body"></i>
                                                        <input type="text" class="form-control"> </div>
                                                </div>
                                                <div class="form-group has-warning">
                                                    <label class="control-label">Input with warning</label>
                                                    <div class="input-icon right">
                                                        <i class="fa fa-warning tooltips" data-original-title="please provide an email" data-container="body"></i>
                                                        <input type="text" class="form-control"> </div>
                                                </div>
                                                <div class="form-group has-error">
                                                    <label class="control-label">Input with error</label>
                                                    <div class="input-icon right">
                                                        <i class="fa fa-exclamation tooltips" data-original-title="please write a valid email" data-container="body"></i>
                                                        <input type="text" class="form-control"> </div>
                                                </div>
                                            </div>
                                            <div class="form-actions right">
                                                <button type="button" class="btn default">Cancel</button>
                                                <button type="submit" class="btn green">Submit</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="portlet box purple">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="fa fa-gift"></i> Horizontal Form Validation States </div>
                                        <div class="tools">
                                            <a href="" class="collapse"> </a>
                                            <a href="#portlet-config" data-toggle="modal" class="config"> </a>
                                            <a href="" class="reload"> </a>
                                            <a href="" class="remove"> </a>
                                        </div>
                                    </div>
                                    <div class="portlet-body form">
                                        <form role="form" class="form-horizontal">
                                            <div class="form-body">
                                                <div class="form-group">
                                                    <label class="col-md-4 control-label">Default input</label>
                                                    <div class="col-md-8">
                                                        <div class="input-icon right">
                                                            <i class="fa fa-info-circle tooltips" data-original-title="Email address" data-container="body"></i>
                                                            <input type="text" class="form-control"> </div>
                                                    </div>
                                                </div>
                                                <div class="form-group has-success">
                                                    <label class="col-md-4 control-label">Input with success</label>
                                                    <div class="col-md-8">
                                                        <div class="input-icon right">
                                                            <i class="fa fa-check tooltips" data-original-title="You look OK!" data-container="body"></i>
                                                            <input type="text" class="form-control"> </div>
                                                    </div>
                                                </div>
                                                <div class="form-group has-warning">
                                                    <label class="col-md-4 control-label">Input with warning</label>
                                                    <div class="col-md-8">
                                                        <div class="input-icon right">
                                                            <i class="fa fa-warning tooltips" data-original-title="please provide an email" data-container="body"></i>
                                                            <input type="text" class="form-control"> </div>
                                                    </div>
                                                </div>
                                                <div class="form-group has-error">
                                                    <label class="col-md-4 control-label">Input with error</label>
                                                    <div class="col-md-8">
                                                        <div class="input-icon right">
                                                            <i class="fa fa-exclamation tooltips" data-original-title="please write a valid email" data-container="body"></i>
                                                            <input type="text" class="form-control"> </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-actions">
                                                <div class="row">
                                                    <div class="col-md-offset-4 col-md-8">
                                                        <button type="button" class="btn default">Cancel</button>
                                                        <button type="submit" class="btn blue">Submit</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row ">
                            <div class="col-md-12">
                                <!-- BEGIN SAMPLE FORM PORTLET-->
                                <div class="portlet light bordered">
                                    <div class="portlet-title">
                                        <div class="caption">
                                            <i class="icon-share font-dark"></i>
                                            <span class="caption-subject font-dark bold uppercase">More Form Samples</span>
                                        </div>
                                        <div class="actions">
                                            <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                                                <i class="icon-cloud-upload"></i>
                                            </a>
                                            <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                                                <i class="icon-wrench"></i>
                                            </a>
                                            <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                                                <i class="icon-trash"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="portlet-body">
                                        <h4>Inline Form</h4>
                                        <form class="form-inline" role="form">
                                            <div class="form-group">
                                                <label class="sr-only" for="exampleInputEmail2">Email address</label>
                                                <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Enter email"> </div>
                                            <div class="form-group">
                                                <label class="sr-only" for="exampleInputPassword2">Password</label>
                                                <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password"> </div>
                                            <label class="mt-checkbox">
                                                <input type="checkbox"> Remember me
                                                <span></span>
                                            </label>
                                            <button type="submit" class="btn btn-default">Sign in</button>
                                        </form>
                                        <hr>
                                        <h4>Inline Form With Icons</h4>
                                        <form class="form-inline" role="form">
                                            <div class="form-group">
                                                <label class="sr-only" for="exampleInputEmail22">Email address</label>
                                                <div class="input-icon">
                                                    <i class="fa fa-envelope"></i>
                                                    <input type="email" class="form-control" id="exampleInputEmail22" placeholder="Enter email"> </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="sr-only" for="exampleInputPassword42">Password</label>
                                                <div class="input-icon">
                                                    <i class="fa fa-user"></i>
                                                    <input type="password" class="form-control" id="exampleInputPassword42" placeholder="Password"> </div>
                                            </div>
                                            <label class="mt-checkbox">
                                                <input type="checkbox"> Remember me
                                                <span></span>
                                            </label>
                                            <button type="submit" class="btn btn-default">Sign in</button>
                                        </form>
                                        <hr>
                                        <h4>Horizontal Form</h4>
                                        <form class="form-horizontal" role="form">
                                            <div class="form-group">
                                                <label for="inputEmail1" class="col-md-2 control-label">Email</label>
                                                <div class="col-md-4">
                                                    <input type="email" class="form-control" id="inputEmail1" placeholder="Email"> </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputPassword12" class="col-md-2 control-label">Password</label>
                                                <div class="col-md-4">
                                                    <input type="password" class="form-control" id="inputPassword12" placeholder="Password"> </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-offset-2 col-md-4">
                                                    <label class="mt-checkbox">
                                                        <input type="checkbox"> Remember me
                                                        <span></span>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-offset-2 col-md-10">
                                                    <button type="submit" class="btn blue">Sign in</button>
                                                </div>
                                            </div>
                                        </form>
                                        <hr>
                                        <h4>Horizontal Form With Icons</h4>
                                        <form class="form-horizontal" role="form">
                                            <div class="form-group">
                                                <label for="inputEmail12" class="col-md-2 control-label">Email</label>
                                                <div class="col-md-4">
                                                    <div class="input-icon">
                                                        <i class="fa fa-envelope"></i>
                                                        <input type="email" class="form-control" id="inputEmail12" placeholder="Email"> </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputPassword1" class="col-md-2 control-label">Password</label>
                                                <div class="col-md-4">
                                                    <div class="input-icon right">
                                                        <i class="fa fa-user"></i>
                                                        <input type="password" class="form-control" id="inputPassword1" placeholder="Password"> </div>
                                                    <div class="help-block"> with right aligned icon </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-offset-2 col-md-4">
                                                    <label class="mt-checkbox">
                                                        <input type="checkbox"> Remember me
                                                        <span></span>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-offset-2 col-md-10">
                                                    <button type="submit" class="btn green">Sign in</button>
                                                </div>
                                            </div>
                                        </form>
                                        <hr>
                                        <h4>Column Sizing</h4>
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-2">
                                                    <input type="text" class="form-control" placeholder=".col-md-2"> </div>
                                                <div class="col-md-3">
                                                    <input type="text" class="form-control" placeholder=".col-md-3"> </div>
                                                <div class="col-md-4">
                                                    <input type="text" class="form-control" placeholder=".col-md-4"> </div>
                                                <div class="col-md-3">
                                                    <input type="text" class="form-control" placeholder=".col-md-2"> </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!-- END SAMPLE FORM PORTLET-->
                            </div>
                        </div>
                    </div>
                    <!-- END CONTENT BODY -->
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="/jsp/inc/js-link-metronic.inc"%>

    
</body>
</html>