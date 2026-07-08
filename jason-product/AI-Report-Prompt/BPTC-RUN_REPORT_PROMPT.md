# BPTC Membership Report Regeneration Prompt

Use this prompt to regenerate the committee membership report from future CSV exports:

```text
Generate an updated BPTC committee membership report from the latest membership CSV exports in this folder.

Use these files:
- BPTC_Active_*.csv for current active members
- BPTC_Lapsed_*.csv for lapsed members
- BPTC_Cancelled_*.csv for cancelled members

Create an output folder called committee_membership_report.

Produce:
1. A Markdown report called BPTC_Committee_Membership_Report.md
2. A PDF version called BPTC_Committee_Membership_Report.pdf
3. Chart PNGs for:
   - KPI dashboard
   - monthly membership trend
   - year-over-year comparison
   - age distribution
   - gender distribution
   - membership type distribution
   - retention by membership type
   - renewal rate trend
4. A CSV summary called membership_kpi_summary.csv

Use the same definitions as the previous report:
- Total members = active paid records excluding pending records
- New members YTD = unique people whose Date joined venue is in the report year
- Inferred renewals YTD = active paid records whose current Start Date is in the report year but whose Date joined venue is before the report year
- Expired members YTD = unique lapsed people whose Expiry Date is in the report year
- Net membership growth = new members YTD minus expired members YTD
- Growth rate = net growth divided by estimated opening membership base
- Renewal rate = inferred renewals / (inferred renewals + expired members)
- First-year retention = prior-year joiners who are currently active
- Multi-year retention = people who joined before the prior year and are currently active

Keep the report committee-ready: concise executive summary, clear KPIs, charts, management commentary, risks, and practical recommendations. Also save any generation script so the report can be rerun later.
```

Recommended addition for future runs:

```text
Also create a reusable script named generate_membership_report.py.
```
