<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Index</title>
</head>
<body>
<div id="page-content-wrapper">
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-12 column">

                <h1>Input a form to generate a poster:</h1>
                <form role="form" method="post" action="/poster/generate">
                    <div class="form-group">
                        <label>Company Name</label>
                        <input name="companyName" type="text" class="form-control"
                               value="${(companyInfo.companyName)!''}"/>
                    </div>
                    <div class="form-group">
                        <label>Company Logo Url</label>
                        <input name="companyLogo" type="text" class="form-control"
                               value="${(companyInfo.companyLogo)!''}"/>
                    </div>
                    <div class="form-group">
                        <label>Company Website Url</label>
                        <input name="companyWebsite" type="text" class="form-control"
                               value="${(companyInfo.companyWebsite)!''}"/>
                    </div>
                    <button type="submit" class="btn btn-default" formtarget="_blank">Submit to produce a poster</button>
                </form>

            </div>
        </div>
    </div>
</div>

</body>
</html>