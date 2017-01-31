<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>IT Jobs Board Europe</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content="IT JobsBoard Europe - find the perfect job in Europe"/>
    <meta name="keywords" content="IT, Jobsboard, Europe"/>

    <style type="text/css">
        [ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
            display: none !important;
        }
    </style>

    %{-- ######  App theme--}%
    <link rel="stylesheet" href="/assets/ux.application.css">

    <link rel="shortcut icon" href="/assets/ux/favicon.ico">

    <script type="text/javascript">
        window.contextPath = "";
    </script>

    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
                (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

        ga('create', 'UA-90999958-1', 'auto');

        ga('send', 'pageview', location.pathname);

        ga('send', {
            hitType: 'event',
            eventCategory: 'page',
            eventAction: 'view',
            eventLabel: location.pathname
        });
    </script>
</head>

<body ng-app="app.web" ng-controller="IndexController as indexCtrl">

    <!-- navbar-->
    <header class="header">
        <div role="navigation" class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a href="/" class="navbar-brand">
                        <img src="assets/ux/logo.png" alt="logo" class="hidden-xs hidden-sm">
                        <img src="assets/ux/logo-small.png" alt="logo" class="visible-xs visible-sm">
                        <span class="sr-only">Go to homepage</span>
                    </a>
                    <div class="navbar-buttons">
                        <button type="button" data-toggle="collapse" data-target=".navbar-collapse" class="navbar-toggle navbar-btn">Menu<i class="fa fa-align-justify"></i></button>
                    </div>
                </div>
                <div id="navigation" class="collapse navbar-collapse navbar-right">
                    <ul class="nav navbar-nav">
                        <li><a href="/#jobs">Jobs</a></li>
                        <li><a href="/#about">About us</a></li>
                        <li><a href="/#faq">FAQ</a></li>
                        <li><a href="/#contact">Contact</a></li>
                    </ul>
                    <a href="#" data-toggle="modal" data-target="#login-modal" class="btn navbar-btn btn-white">
                        <i class="fa fa-sign-in"></i>Log in
                    </a>
                </div>
            </div>
        </div>
    </header>

    <!-- *** LOGIN MODAL ***_________________________________________________________
        -->
    <div id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true" class="modal fade">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" data-dismiss="modal" aria-hidden="true" class="close">×</button>
                    <h4 id="Login" class="modal-title">Customer login</h4>
                </div>
                <div class="modal-body">
                    <form action="customer-orders.html" method="post">
                        <div class="form-group">
                            <input id="email_modal" type="text" placeholder="email" class="form-control">
                        </div>
                        <div class="form-group">
                            <input id="password_modal" type="password" placeholder="password" class="form-control">
                        </div>
                        <p class="text-center">
                            <button type="button" class="btn btn-primary"><i class="fa fa-sign-in"></i> Log in</button>
                        </p>
                    </form>
                    <p class="text-center text-muted">Not registered yet?</p>
                    <p class="text-center text-muted"><a href="/"><strong>Register now</strong></a>! It is easy and done in 1 minute and gives you access to special discounts and much more!</p>
                </div>
            </div>
        </div>
    </div>
    <!-- *** LOGIN MODAL END ***-->

    <div ui-view></div>

    <footer class="footer">
        <div class="footer__block">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-sm-12">
                        <h4>About itjobsboard.eu</h4>
                        <p>ITJOBSBOARD.EU is a <strong>jobs board</strong> for the European Union</p>
                        <p> We reflect the IT market in Europe </p>
                    </div>
                    <div class="col-md-4 col-sm-6">
                        <h4>Links</h4>
                        <ul>
                            <li><a href="/">Home</a></li>
                            <li><a href="/#jobs">Jobs Listing</a></li>
                            <li><a href="/#about">About</a></li>
                            <li><a href="/#contact">Contact</a></li>
                            <li><a href="/#faq">FAQ</a></li>
                        </ul>
                    </div>
                    <div class="col-md-4 col-sm-6">
                        <h4>Our office</h4>
                        <h5>Bucharest</h5>
                        <p> Cristescu Dima<br />nr. 7<br />mobile: 0040743163039</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 col-sm-6">
                        <h4>Client zone</h4>
                        <ul>
                            <li><a href="/">Login or Register</a></li>
                            <li><a href="/">Post a new job</a></li>
                        </ul>
                    </div>
                    <div class="col-md-4 col-sm-6">
                        <h4>Let's be Friends</h4>
                        <p class="social"><a href="#" data-animate-hover="pulse" class="external facebook"><i class="fa fa-facebook"></i></a><a href="#" data-animate-hover="pulse" class="external gplus"><i class="fa fa-google-plus"></i></a><a href="#" data-animate-hover="pulse" class="external twitter"><i class="fa fa-twitter"></i></a><a href="#" data-animate-hover="pulse" class="email"><i class="fa fa-envelope"></i></a></p>
                    </div>
                    <div class="col-md-4 col-sm-12">
                        <h4>News and Updates</h4>
                        <p>Sign up to get weekly portion of fresh jobs and news from us.</p>
                        <form class="footer__newsletter">
                            <div class="input-group">
                                <input type="text" placeholder="Enter your email address" class="form-control"><span class="input-group-btn">
                                <button type="button" class="btn btn-default"><i class="fa fa-send"></i></button></span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer__copyright">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <p>&copy;2017 ItJobsBoard.EU</p>
                    </div>
                    <div class="col-md-6">
                        <p class="credit pull-right">Sample for <a href="https://grailsthreebook.com">Grails 3 Book</a></p>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <script src="/assets/app/web/ux.app.web.js"> </script>

    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;amp;sensor=false"></script>

</body>
</html>
