# AWS Simple Pricing
AWS pricing model is rather complicated, a simplifed view is needed to look over Amazon's tricks of over charging.

AWS will begin the charging directly after 12 months' free, so stop the services if you don't use anymore to avoid any charge.
The instances are not the cheapest, but the one that could serve as a actual use as a Developer or a small application.
All the price are of USD.

## AWS instance

| Type     |  Details     |
| -------- | ------------ |
| t2.micro | 1vCPU, 1GB memory |
| t2.small | 1vCPU, 2GB memory |
| t3.micro | 2vCPU, 1GB memory |
| gp3      | General Purpose SSD |
| 1 ACU    | 2GB memory |
| WCU      | Write capacity unit - 1 WCU can write 4kb data / second to DB | 
| RCU      | Read capacity unit - 1 RCP can read 4kb data / second from DB |
| SQS Request | Every Amazon SQS action counts as a request - contain up to 10 messages, 64KB data |


## AWS Simple Price - In progress

| AWS Service   |  Price       | Free Tier |
| ------------- | ------------ | --------- |
| EC2 | reserved t2.small - $126/year |  12 months - t2.micro instance  |
| S3  | standard storage <br/> - $0.023/GB-month Storage; <br/> - $0.005/1000 Put or list request <br/> - $0.0004/1000 Get requests | 12 months - 5GB standard storage |
| RDS | db.t3.micro reserved instance <br/> - instance $106/year <br/> - storage $0.115/GB-month  | 12 months - t2.micro <br/> - 20G storage, 20G backup or snapshot |
| Aurora(serveless) | $0.12/ACU Hour <br/> $0.10/GB-month Storage <br/> $0.20 per 1 million requests <br/> $0.2/G backup | No Free Tier |
| API Gateway | $1/million Http API Call or $3.5/million Rest API Call | 12 months - 1 million call |
| Lambda | $0.06/GB-hour calculation <br/> $0.20/million requests | Always - monthly <br/> - 888 GB-hour calculation <br> - 1 million requests |
| SNS | $0.5/million API request <br/> $2.00/100_000 email notification | Always - monthly <br/> - 1 million publication <br/> - 1000 email notification <br/> - 100,000 http push <br/> - 1 million mobile push |
| SQS | $0.40/million requests | Always - monthly <br/> - 1 million requests / month |
| EBS | gp3 - $0.08/GB-month | |
| EFS | standard storage - $0.3/GB-month | 12 months - 5 GB standard storage |
| DynamoDB | storage $0.25/GB-mth <br/> provisioned <br/> - $0.25/million RCU <br/> - $1.25/million WCU used | always <br/> - storage - 25G <br/> - provisioned instance - free for  25 RCU and 25 WCU <br/> - (no free tier for on-demand instance) |
| CloudWatch | $0.30/metrics-month <br/> $3/dashboard-month <br/> $0.1/alarm-month <br/> $0.5/GB-month log | always <br/> - basic monitoring metrics <br/> - 1 detailed monitoring metrics <br/> - 10 alarm metrics <br/> - 5GB data log <br/> - 3 Dashboard and up to 50 metrics / month |
| CloudFormation | $0.9 /1000 handler operation | always - monthly <br/> - 1,000 handler operations / month / account |
| X-Ray | $5.00 per 1 million traces recorded <br/> $0.50 per 1 million traces retrieved <br/> $0.50 per 1 million traces scanned | always - monthly <br> -  100,000 traces recorded/month <br/> - 1,000,000 traces retrieved or scanned/month |
| DAX | |
| ECS | |
| ELB | |
| Route 53 |
| ASG | |
| SES | |
| ElastiCache | |
| CloudFront | |
| Athena | |
| Global Accelerator | |
| Snow | |
| Storage Gateway | |
| FSx | |
| Kinesis | |
| Fargate | |
| ECR | |
| Cognito | |
| SSO | |
| Config | |
| WAF | |
| Shield | |
| Security Manager | |
| DataSync | |
| VPN Endpoint | |
| PrivateLink | |
| Organisations | |
| RAM | |
| KMS | |
| CloudHSM | |
| Direct Connect | |
| DMS | |
| SMS | |
| Step Function | |
| Trusted Advisor | |
| SSM | |